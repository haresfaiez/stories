package stories.event

import org.joda.time.DateTime

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~/^"([^"]*)" is an event at "([^"]*)" in "([^"]*)"$/) {
    String event, String time, String location ->
        thisEvening = new DateTime(2016, 01, 01, 21, 00)
        destination = Event.entitled(event, thisEvening, location)
}

Given(~/^Emma is participant of that event$/) { ->
    emma = Participant.named("Emma", destination)
}



When(~/^Emma updates the event with "([^"]*)" at "([^"]*)"$/) {
    String update, String time ->
        message        = update
        updateTime     = new DateTime(2016, 01, 01, 21, 00)
        destination.update(emma, update, updateTime)
}

Then(~/^the event stream should include that update$/) { ->
    updateOfEmma = new Update(emma, message, updateTime)
    destination.stream().contains(updateOfEmma)
}
