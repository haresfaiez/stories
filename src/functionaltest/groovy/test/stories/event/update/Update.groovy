package test.stories.event.update

import sun.security.util.PendingException

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~/^Emma participates to "([^"]*)"$/) { String event ->
    throw new PendingException()
}

When(~/^Emma updates the event with "([^"]*)"$/) { String update ->
    throw new PendingException()
}

Then(~/^all visitors of the event should get that update$/) { ->
    throw new PendingException()
}


