# YMF (YattaY Map File) Specs

This file format is meant to carry all the informations about a level for the game YattaY.

## Version 0 (tmp)

### Fields & sizes
|Field |Description| Size |
|:-----|:----------|:-----|
|Version| Version of the file format |1 byte|
|Width  | Width of the level | 4 bytes|
|Height | Height of the level| 4 bytes|
|SpawnRed| Coord of the red team spawn| 8 bytes|
|SpawnBlue| Coord of the blue team spawn| 8 bytes|
|Blocks | All blocks in the level| n*(4 byte)|

### Example

|Field | Value |
|:-----|:------|
|Version| 0x00 |
|Width  | 1000 |
|Height | 200  |
|SpawnRed| 0x0000005A 0x00000500 (X&Y)|
|SpawnBlue|0x00000300 0x00000500 (X&Y)|
|Blocks | 0x00000000 0x00000004 0x00000003 0x0000000A 0x00000002 0x7 0x00000009...|
