package stories.event

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~/^Emma is participant of "([^"]*)"$/) { String event ->
    destination = Event.entitled(event)
    emma        = Participant.named("Emma", destination)
}

When(~/^Emma updates the event with "([^"]*)"$/) { String update ->
    message = update
    destination.update(emma, update)
}

Then(~/^the event stream should include that update$/) { ->
    updateOfEmma = Update.from(emma, message)
    destination.stream().contains(updateOfEmma)
}
