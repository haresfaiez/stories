package stories.event

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~/^Emma is participant of "([^"]*)"$/) { String event ->
    destination = Event.entitled(event)
    emma        = Participant.named("Emma", destination)
}

When(~/^Emma updates the event with "([^"]*)" at "([^"]*)"$/) {
    String update, String time ->
    message = update
    destination.update(emma, update, now)
}

Then(~/^the event stream should include that update$/) { ->
    updateOfEmma = Update.from(emma, message, aTime())
    destination.stream().contains(updateOfEmma)
}
