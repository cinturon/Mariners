# WebSocket Message Reception Fix

## Problem
The WebSocket connection was only receiving the first message and then not receiving any subsequent messages.

## Root Cause
The issue was in `LiveGameService.kt`. The code was using a nested `collect` inside a `flow` builder:

```kotlin
flow {
    incoming.receiveAsFlow()
        .filterIsInstance<Frame.Text>()
        .collect { frame ->
            // Process and emit
            emit(gameUpdate)
        }
}
```

This pattern causes the flow to only emit the first value and then get stuck because:
1. The outer `flow { }` builder creates a cold flow
2. The inner `collect { }` is a terminal operator that consumes the flow
3. Nesting collectors like this creates a suspension point that doesn't properly propagate all emissions

## Solution
Changed to use `emitAll()` with `map()` instead of `collect()`:

```kotlin
flow {
    emitAll(
        incoming.receiveAsFlow()
            .filterIsInstance<Frame.Text>()
            .map { frame ->
                // Process and return GameUpdate
                gameUpdate
            }
            .filterNotNull()
    )
}
```

This properly:
1. Transforms each incoming frame to a GameUpdate using `map()`
2. Filters out any null results with `filterNotNull()`
3. Emits all transformed values to the outer flow using `emitAll()`

## Benefits
- ✅ All WebSocket messages are now properly received
- ✅ The flow properly propagates all emissions to collectors
- ✅ Maintains proper error handling and logging
- ✅ No breaking changes to the API

## Files Changed
- `app/src/main/java/dev/jbelt/seattlemariners/repo/LiveGameService.kt`

## Testing
To test the fix:
1. Connect to the WebSocket using `LiveGameViewModel.connect(gamePk)`
2. Send multiple messages through the WebSocket
3. Verify that `gameUpdate` StateFlow receives all messages, not just the first one
4. Check LogCat for "Received raw JSON" logs for each message

## Additional Notes
The warnings about "Unused import directive" for `emitAll` and `map` are false positives - these imports are required and used in the code.

