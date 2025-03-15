MERGE INTO PLAYER (id, user_name, lichess_user_name) KEY(id)
VALUES (10000, 'foobar', null),
(10001, 'drdrunkenstein', null),
(10002, 'MagnusC', 'drdrunkenstein');

MERGE INTO RESULT (id, winner, reason)
VALUES (10000, 'WHITE', 'CHECK_MADE');

MERGE INTO MOVE (id, field, figure, has_captured, is_made, result_id)
VALUES (10000, 'e4', null, 0, 0, 10000),
(10001, 'e5', null, 0, 0, 10000),
(10002, 'c4', 'B', 0, 0, 10000),
(10003, 'c6', 'B', 0, 0, 10000),
(10004, 'h5', 'Q', 0, 0, 10000),
(10005, 'f6', 'N', 0, 0, 10000),
(10006, 'f7', 'Q', 1, 1, 10000);

MERGE INTO MATCH (match_id, played_at, played_with, user_id)
VALUES ('00000000-0000-0000-0001-000000000001', '2024-10-18T12:30:00+02', 'WHITE', 10000);

MERGE INTO MATCH (match_id, played_at, played_with, result_id, user_id)
VALUES ('00000000-0000-0000-0001-000000000002', '2024-10-19T05:07:00+02', 'BLACK', 10000, 10000);
