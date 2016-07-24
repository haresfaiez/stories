package stories.event

import org.joda.time.DateTime

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~/^Emma is participant of "([^"]*)"$/) { String event ->
    destination = Event.entitled(event)
    emma        = Participant.named("Emma", destination)
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
