Story: The Berlin Clock

Meta:
@scope JMP-BDD

Narrative:
	As a clock hipster
	I want to tell the time using the Berlin Clock
	So that I can increase then number of ways that I can read the time

Scenario: Midnight
Given new time converter
When the time is 00:00:00
Then the clock should look like
Y
OOOO
OOOO
OOOOOOOOOOO
OOOO

Scenario: Just before midnight
Given new time converter
When the time is 23:59:59
Then the clock should look like
O
RRRR
RRRO
YYRYYRYYRYY
YYYY

Scenario: Just After midnight
Given new time converter
When the time is 00:00:01
Then the clock should look like
O
OOOO
OOOO
OOOOOOOOOOO
OOOO

Scenario: Middle of the afternoon
Given new time converter
When the time is 13:17:01
Then the clock should look like
O
RROO
RRRO
YYROOOOOOOO
YYOO

