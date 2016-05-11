CREATE p =(bill:Person {id: "32b0a8e0-0a3d-11e6-8cf0-2d237e461979", name: "Bill" })

CREATE e =(emma:Person {id: "32b0a8e0-0a3d-11e6-8cf0-2d237e461988", name: "emma" })

CREATE b =(brook:Person {id: "32b0a8e0-0a3d-11e6-8cf0-2d237e461978", name: "Brook" })

MATCH ((bill:Person {id: "32b0a8e0-0a3d-11e6-8cf0-2d237e461979"})),
      (emma:Person {id: "32b0a8e0-0a3d-11e6-8cf0-2d237e461988"})
CREATE  (bill)-[follow:FOLLOWS]->(emma)
RETURN follow

MATCH ((bill:Person {id: "32b0a8e0-0a3d-11e6-8cf0-2d237e461979"})),
      (brook:Person {id: "32b0a8e0-0a3d-11e6-8cf0-2d237e461978"})
CREATE  (bill)-[follow:FOLLOWS]->(brook)
RETURN follow

MATCH (target: Person {id: "32b0a8e0-0a3d-11e6-8cf0-2d237e461979"})
RETURN target

