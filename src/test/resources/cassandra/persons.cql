CREATE p =(bill:Person {id: "32b0a8e0-0a3d-11e6-8cf0-2d237e461979", name: "Bill" })
RETURN p

MATCH (target: Person {id: "32b0a8e0-0a3d-11e6-8cf0-2d237e461979"})
RETURN target

[{"query" : "MATCH (target: Person {id: "32b0a8e0-0a3d-11e6-8cf0-2d237e461979"}) RETURN target","params" : { }}]


String.format("{\"query\" : \"MATCH (target: Person {id: \\\"%s\\\"}) RETURN target\", \"params\" : { }}", query);