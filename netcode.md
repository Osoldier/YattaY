# YattaY netcode

## Specifications

- Tickrate: 128 ticks/s
- Opcode: 8 bits


## Communication

### TCP

TCP opcodes goes from 00 to 127

| Opcode | Args                      | Meaning                                               | Sender | Priority |
|:-------|:--------------------------|:------------------------------------------------------|:-------|:---------|
| 00     | (Nickname) [sess_id]      | I want to join session sess_id                        | Client | 1        |
| 01     | (Nickname) (userid)       | You joined a session, your uid is userid              | Server | 1        |
| 02     | (Nickname)                | Couldn't join a session (session full or server full) | Server | 1        |
| 03     | (userid) [reason]         | You got kicked                                        | Server | 1        |
| 04     | (userid)                  | I want to leave                                       | Client | 1        |
| 05     | (userid)                  | Kthxbye                                               | Server | 1        |
| 10     | (userid)                  | starting to send level                                | Server | 2        |
| 11     | (userid) (width) (height) | Dimensions of the level                               | Server | 2        |
| 12     | (userid) (blockid) (id)   | id-th block of the level is a blockid                 | Server | 2        |
| 13     | (userid) (x) (y)          | Blue spawn is at (x,y)                                | Server | 2        |
| 14     | (userid) (x) (y)          | Red spawn is at (x,y)                                 | Server | 2        |
| 20     | (userid) (timestamp)      | ping / ping response                                  | Any    | 0        |

### UDP

UDP opcodes goes from 128 to 255

| Opcode | Args               | Meaning | Sender |
|:-------|:-------------------|:--------|:-------|
| 128    | 
