package stories.event

import cucumber.api.DataTable
import org.joda.time.DateTime

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)


Given(~/^the event$/) { DataTable eventRaw ->

    def event = eventRaw.asMaps(String.class, String.class).get(0)

    String title    = event.get('Title')
    thisEvening     = new DateTime(event.get('Time'))
    String location = event.get('Location')

    destination     = Event.entitled(title, thisEvening, location)
}

Given(~/^Emma is participant of that event$/) { ->
    emma = new Participant("Emma", destination)
}

When(~/^Emma updates the event with$/) { DataTable updateRaw ->

    def update = updateRaw.asMaps(String.class, String.class).get(0)

    updateMessage = update.get('Message')
    updateTime    = new DateTime(update.get('Time'))

    destination.update(emma, updateMessage, updateTime)
}

Then(~/^the event stream should include that update$/) { ->
    updateOfEmma = new Update(emma, updateMessage, updateTime)

    assert destination.stream().contains(updateOfEmma)
}
