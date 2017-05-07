# YattaY netcode

## Specifications

- Tickrate: 128 ticks/s
- Opcode: 8 bits


## Communication

### TCP

TCP opcodes goes from 00 to 127

| Opcode | Args                         | Meaning                                               | Sender | Priority | Permission Level |
|:-------|:-----------------------------|:------------------------------------------------------|:-------|:---------|:-----------------|
| 00     | (Nickname) [sess_id]         | I want to join session sess_id                        | Client | 1        | 0                |
| 01     | (Nickname) (userid) (sess_id)| You joined a session, your uid is userid              | Server | 1        | 0                |
| 02     | (Nickname) (userid) [reason] | Couldn't join a session                               | Server | 1        | 0                |
| 03     | (userid) [reason]            | Kick user userid for reason                           | Client | 1        | 1                |
| 04     | (userid)                     | I want to leave                                       | Client | 1        | 0                |
| 05     | (userid)                     | Kthxbye                                               | Server | 1        | 0                |
| 10     | (userid)                     | starting to send level                                | Server | 2        | 0                |
| 11     | (userid) (width) (height)    | Dimensions of the level                               | Server | 2        | 0                |
| 12     | (userid) (blockid) (id)      | id-th block of the level is a blockid                 | Server | 2        | 0                |
| 13     | (userid) (x) (y)             | Blue spawn is at (x,y)                                | Server | 2        | 0                |
| 14     | (userid) (x) (y)             | Red spawn is at (x,y)                                 | Server | 2        | 0                |
| 20     | (timestamp)                  | ping / ping response                                  | Any    | 0        | 0                |
| 21     | (userid)                     | I want to change team                                 | Client | 1        | 0                |
| 22     | (userid)                     | Team changed                                          | Server | 1        | 0                |
| 23     | (userid)                     | Cannot join team                                      | Server | 1        | 0                |

### UDP

UDP opcodes goes from 128 to 255

| Opcode | Args               | Meaning | Sender |
|:-------|:-------------------|:--------|:-------|
| 128    | 


## Protocol example

### Connexion
| Client | Server              |
|:-------|--------------------:|
|00 John |					   |
|		 | 01 24545 2		   |
|		 | 10 24545			   |
|		 | 11 24545 1000 200   |
|		 | 12 24545 0 1        |
|		 | 12 24545 1 1        |
|		 | 12 24545 2 4        |
|		 | ... 			       |
|		 | 13 24545 20 100     |
|		 | 14 24545 15980 100  |
