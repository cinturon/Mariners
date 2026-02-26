# MLB StatsAPI WebSocket Integration Guide

## Queries and Examples

### WebSocket Connection
**URL**: `wss://ws.statsapi.mlb.com/api/v1/game/push/subscribe/gameday/{gamePk}`

Example in code:
```kotlin
session = client.webSocketSession("wss://ws.statsapi.mlb.com/api/v1/game/push/subscribe/gameday/717325")
```

### Live Feed Endpoint (HTTP)
**URL**: `https://statsapi.mlb.com/api/v1/game/{gamePk}/linescore`

Get just current play:
```kotlin
// Full response
https://statsapi.mlb.com/api/v1/game/{gamePk}/feed/live

// Just plays
https://statsapi.mlb.com/api/v1/game/{gamePk}/feed/live?fields=liveData.plays.currentPlay
```

---

## Data Flow

### 1. WebSocket Message Format
```json
{
    "timeStamp": "20230721_182018",      // yyyyMMdd_HHmmss format
    "gamePk": "717325",
    "updateId": "7a5b0181-f833-4de8-bf1b-65de5e3ef6a3",
    "wait": 10,
    "logicalEvents": ["countChange", "count10", "basesEmpty"],
    "gameEvents": ["ball"],
    "changeEvent": {
        "type": "new_entry"
    },
    "currentPlay": {
        // Optional: Full play-by-play data
        "result": {...},
        "about": {...},
        "playEvents": [...]
    }
}
```

### 2. Data Model Parsing
The app uses Moshi JSON adapter to deserialize WebSocket messages into:
```kotlin
GameUpdate(
    timeStamp: String,           // Raw format: yyyyMMdd_HHmmss
    gamePk: String,              // Game ID
    updateId: String,            // Unique update ID
    wait: Int,                   // Milliseconds to next update
    logicalEvents: List<String>, // "countChange", "basesEmpty", etc.
    gameEvents: List<String>,    // "ball", "strikeout", etc.
    changeEvent: ChangeEvent,    // Change type info
    currentPlay: CurrentPlayData? = null  // Full play details if available
)
```

### 3. Timestamp Parsing
Raw timestamp `"20230721_182018"` is converted to readable format:
```kotlin
val parsed = gameUpdate.getParsedTimestamp()
// Returns: "07/21/2023 18:20:18"
```

---

## CurrentPlay Structure (8th.json)

The `CurrentPlayData` class handles the complete structure:

```kotlin
CurrentPlayData(
    result: PlayResult,              // Out type, event, RBI, scores
    about: PlayAbout,                // Inning, timing, complete status
    count: PlayCount,                // Balls, strikes, outs
    matchup: PlayMatchup,            // Batter, pitcher, handedness
    pitchIndex: List<Int>,           // Which pitches in this event
    runners: List<RunnerData>,       // Runners and their movement
    playEvents: List<PlayEventData>, // Individual pitches (pitch data, location)
    playEndTime: String,             // ISO timestamp
    atBatIndex: Int                  // At-bat number in game
)
```

---

## Key Fields in PlayEventData (Individual Pitches)

For each pitch, the following information is available:

```kotlin
PlayEventData(
    details: PlayEventDetailsData(
        call: CallData,           // "Ball", "Strike", "Foul", etc.
        description: String,      // Human readable
        type: PitchTypeData,      // "FF" (Four-Seam), "CU" (Curveball), etc.
        isOut: Boolean
    ),
    count: PlayCount,             // Running count after this pitch
    pitchData: PitchDataInfo(
        startSpeed: Double,       // mph at release
        endSpeed: Double,         // mph at plate
        coordinates: CoordinatesData(
            pX, pZ: Double,       // Plate location (feet from center)
            x, y: Double,         // Pixel coordinates for visualization
            vX0, vY0, vZ0: Double, // Velocity vectors at release
            aX, aY, aZ: Double    // Acceleration vectors
        ),
        breaks: BreaksData(
            breakVertical: Double,         // Inches
            breakHorizontal: Double,       // Inches
            spinRate: Int,                 // RPM
            spinDirection: Int             // Degrees
        ),
        zone: Int                         // Strike zone region (1-14)
    )
)
```

---

## Strike Zone Diagram (Zone Numbers)

```
     1  2  3
     4  5  6
     7  8  9
    10 11 12
    13 14     <- Out of zone (14 is low and outside)
```

---

## Event Types in GameUpdate

### logicalEvents Examples
- `countChange` - Ball/strike thrown
- `count10` - Full count (3-0, 2-2, etc.)
- `basesEmpty` - Bases changed
- `playerEnter` - New player entered
- `inningEnd` - Inning concluded

### gameEvents Examples
- `ball` - Ball thrown
- `strikeout` - Batter struck out
- `hit` - Ball was hit
- `homerun` - Home run
- `groundout` - Ground out
- `fieldersChoice` - Fielder's choice

---

## Usage in ViewModel

```kotlin
@HiltViewModel
class LiveGameViewModel @Inject constructor(
    private val liveGameService: LiveGameService
) : ViewModel() {

    private val _gameUpdate = MutableStateFlow<GameUpdate?>(null)
    val gameUpdate: StateFlow<GameUpdate?> = _gameUpdate

    fun connect(gamePk: Int) {
        viewModelScope.launch {
            liveGameService.initSession(gamePk).collect { newUpdate ->
                _gameUpdate.value = newUpdate
            }
        }
    }
}
```

---

## UI Integration

The LiveGameCard automatically updates when new messages arrive:

```kotlin
@Composable
fun LiveGameCard(gamePk: Int?) {
    val gameUpdate by viewModel.gameUpdate.collectAsStateWithLifecycle()
    
    // UI recomposes whenever gameUpdate changes
    Column {
        Text("Timestamp: ${gameUpdate?.getParsedTimestamp()}")
        Text("Score: ${gameUpdate?.currentPlay?.result?.awayScore} - ${gameUpdate?.currentPlay?.result?.homeScore}")
        Text("Current: ${gameUpdate?.currentPlay?.result?.description}")
    }
}
```

---

## API Query Examples

### Get Game Schedule
```
GET https://statsapi.mlb.com/api/v1/schedule?sportId=1&startDate=2026-02-23&endDate=2026-02-23
```

### Get Live Feed with Current Play Only
```
GET https://statsapi.mlb.com/api/v1/game/717325/feed/live?fields=liveData.plays.currentPlay
```

### Get Player Info
```
GET https://statsapi.mlb.com/api/v1/people/{playerId}
```

### Get Game Linescore
```
GET https://statsapi.mlb.com/api/v1/game/717325/linescore
```

---

## Debugging Tips

### Check Logs for WebSocket Messages
```
adb logcat | grep "LiveGameService"
```

### Monitor Timestamp Parsing
The app logs raw JSON before parsing:
```
D/LiveGameService: Received raw JSON: {...}
D/LiveGameService: Successfully parsed GameUpdate: ...
```

### Verify Timestamp Format
If `getParsedTimestamp()` returns the raw value, the format doesn't match expected pattern.
Adjust the parsing logic in GameUpdate.kt if needed.

---

## Error Handling

### Position Nullable Issue
Fixed by making all FieldPosition fields nullable:
```kotlin
data class FieldPosition(
    val code: String? = null,
    val name: String? = null,
    val type: String? = null,
    val abbreviation: String? = null
)
```

### Layout Constraints
Fixed by adding `.fillMaxWidth()` to Column in LiveGameCard:
```kotlin
Column(modifier = modifier
    .fillMaxWidth()  // Prevents infinity constraint error
    .padding(16.dp))
```

### Connection Errors
The LiveGameService properly closes sessions and handles exceptions:
```kotlin
finally {
    Log.d(TAG, "WebSocket session closing")
    closeSession()
}
```


