package test.stories.event.update

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~/^Emma is participant of "([^"]*)"$/) { String event ->
    destination = Event.entitled(event)
    emma        = Participant.named("Emma", destination)
}

When(~/^Emma updates the event with "([^"]*)"$/) { String update ->
    event.update(emma, update)
}

Then(~/^the event stream should include that update$/) { ->
    updateOfEmma = new Update(emma, update, destination)
    event.stream().constains(updateOfEmma)
}
