package stories.event

import cucumber.api.PendingException

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~/^there is a concert tonight$/) { ->
    throw new PendingException()
}

Given(~/^Emma wants to attend it$/) { ->
    throw new PendingException()
}

Given(~/^she is unable to do that$/) { ->
    throw new PendingException()
}

When(~/^the concert begins$/) { ->
    throw new PendingException()
}

Then(~/^she should receive all the updates of attendees about it$/) { ->
    throw new PendingException()
}

