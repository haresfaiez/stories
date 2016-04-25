CREATE KEYSPACE Stories WITH replication={'class': 'SimpleStrategy', 'replication_factor': 1};

use Stories;

CREATE TYPE IF NOT EXISTS Event_update (
    attendee_id uuid,
    time 	timestamp,
    message 	text
);

CREATE TABLE IF NOT EXISTS Event(
       id    	 uuid PRIMARY KEY,
       title     text,
       time	 timestamp,
       attendees set<uuid>,
       updates 	 set<frozen<Event_update>>
);

INSERT INTO event (id,
       	    	   title,
		           time,
		           attendees,
		           updates)
       VALUES (32b0a8e0-0a3d-11e6-8cf0-2d237e461979,
       	       'Concert title',
	            1368438171000,
	            {
	                32b0a8e0-0a3d-11e6-8cf0-2d237e461979
	            },
	            {
	                {attendee_id:32b0a8e0-0a3d-11e6-8cf0-2d237e461979, time: 1368438171000, message:'Good'},
	                {attendee_id:32b0a8e0-0a3d-11e6-8cf0-2d237e461979, time: 1368438171011, message:'Happy!!'},
	                {attendee_id:32b0a8e0-0a3d-11e6-8cf0-2d237e461979, time: 1368438171091, message:'Great event'}
	            });