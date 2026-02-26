# Seattle Mariners App - Data Model and UI Fixes

## Summary of Changes

This document outlines all the changes made to fix the baseball app's data models and UI issues.

---

## 1. **New Unified Data Class: CurrentPlayData.kt**

Created a comprehensive, consolidated data class for the 8th.json `currentPlay` structure that unifies all nested JSON objects into a single coherent model.

### Key Features:
- **Single Source of Truth**: One main class `CurrentPlayData` instead of multiple scattered classes
- **Proper Moshi Integration**: All classes decorated with `@JsonClass(generateAdapter = true)`
- **Null Safety**: Proper nullable types for optional fields
- **Default Values**: All properties have sensible defaults
- **Complete Coverage**: Includes all nested structures:
  - Play results and scoring information
  - Play about (inning, timing, etc.)
  - Count information (balls, strikes, outs)
  - Matchup details (batter, pitcher, handedness)
  - Runners and their movement
  - Individual pitch/event data with full coordinates and breaks
  - Pitch speed, trajectory, and spin information

### Classes Included:
- `CurrentPlayData` - Main play container
- `PlayResult` - Result of the play
- `PlayAbout` - Context about the play
- `PlayCount` - Count state
- `PlayMatchup` - Batter/pitcher info
- `RunnerData` - Runner on base
- `PlayEventData` - Individual pitches/events
- `PitchDataInfo` - Complete pitch analytics
- `CoordinatesData` - 3D pitch coordinates
- `BreaksData` - Pitch movement data
- And 15+ supporting data classes

---

## 2. **Fixed FieldPosition Nullable Issue**

### File: `LiveFeedResponse.kt`

**Problem**: FieldPosition was being deserialized with non-nullable fields, causing crashes when position data was missing.

**Solution**: Made all fields in `FieldPosition` nullable with default values:
```kotlin
@JsonClass(generateAdapter = true)
data class FieldPosition(
    @Json(name = "code")
    val code: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "type")
    val type: String? = null,
    @Json(name = "abbreviation")
    val abbreviation: String? = null
)
```

---

## 3. **Fixed Infinite Height Constraints Error**

### File: `LiveGameCard.kt`

**Problem**: `LazyColumn` was receiving unbounded constraints, causing the error:
```
Vertically scrollable component was measured with an infinity maximum height constraints
```

**Solution**: Added `.fillMaxWidth()` modifier to ensure proper size constraints:
```kotlin
Column(modifier = modifier
    .fillMaxWidth()  // <-- Added this
    .padding(16.dp))
```

---

## 4. **Enhanced GameUpdate with Timestamp Parsing**

### File: `GameUpdate.kt`

**New Features**:
- Added optional `currentPlay` field to GameUpdate
- Added `getParsedTimestamp()` helper function that converts:
  - From: `"20230721_182018"`
  - To: `"07/21/2023 18:20:18"`
- Proper error handling for malformed timestamps

```kotlin
fun getParsedTimestamp(): String {
    // Converts yyyyMMdd_HHmmss to MM/dd/yyyy HH:mm:ss
}
```

---

## 5. **Updated LiveGameCard UI**

### File: `LiveGameCard.kt`

**Improvements**:
- Now displays "LIVE UPDATE" header in cyan
- Shows parsed timestamp instead of raw format
- Displays current play details when available:
  - Play description
  - Score (away vs home)
  - Current inning and top/bottom
- Better visual hierarchy with font weights and colors
- Handles both connected and connecting states

---

## 6. **Improved LiveGameService**

### File: `LiveGameService.kt`

**Enhancements**:
- Added missing logging in `closeSession()`
- Better error handling and exception logging
- Clearer status messages for debugging

---

## Architecture Benefits

### Data Model Improvements:
1. **Consolidation**: Multiple related classes unified into one module
2. **Maintainability**: Easier to track changes to the data structure
3. **Reusability**: CurrentPlayData can be used across the app
4. **Type Safety**: Proper Kotlin data classes with Moshi serialization
5. **Flexibility**: Optional fields with sensible defaults

### UI/UX Improvements:
1. **Layout Stability**: Fixed composition constraints
2. **Real-time Updates**: Proper state flow to UI
3. **Better Feedback**: Shows connecting state and live updates
4. **Data Visibility**: Displays play-by-play updates as they arrive

---

## WebSocket Integration

The app now properly:
1. Connects to MLB StatsAPI WebSocket
2. Receives GameUpdate messages in real-time
3. Parses CurrentPlay data when available
4. Updates UI reactively via Compose StateFlow
5. Handles connection errors gracefully

---

## Testing Recommendations

1. **Timestamp Parsing**: Test with various timestamp formats
2. **Null Handling**: Verify app handles missing position/player data
3. **UI Layout**: Confirm no infinite constraint errors in various screen sizes
4. **WebSocket**: Monitor logs during live game connections
5. **Data Serialization**: Validate Moshi adapters with sample JSON

---

## File Changes Summary

| File | Type | Changes |
|------|------|---------|
| `CurrentPlayData.kt` | New | Created comprehensive play data model |
| `GameUpdate.kt` | Modified | Added currentPlay field and timestamp parser |
| `LiveFeedResponse.kt` | Modified | Fixed FieldPosition nullability |
| `LiveGameCard.kt` | Modified | Fixed layout constraints, enhanced display |
| `LiveGameService.kt` | Modified | Added logging to closeSession |

---

## Notes

- All data classes use Moshi `@JsonClass(generateAdapter = true)` for proper serialization
- Nullable fields are properly marked with `?` 
- Default values provided for all optional fields
- Timestamps parse the format: `yyyyMMdd_HHmmss` to user-friendly format
- WebSocket properly emits GameUpdate objects to StateFlow


