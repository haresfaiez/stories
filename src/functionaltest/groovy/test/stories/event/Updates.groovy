package test.stories.event

import stories.event.Event
import stories.person.Attendee
import stories.person.Person
import stories.stream.PersonStream
import stories.event.Note

import java.time.LocalDateTime
import java.time.Month

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~/^there is a concert tonight$/) { ->
    thisNight = LocalDateTime.of 2015, Month.APRIL, 19, 20, 30;
    // The aggregate Event contains: Attendee, EventUpdate
    concert = new Event(1L, "Concert title", thisNight)

    def bill = new Attendee(new Person(1L, "Bill"))
    bill.attend concert
    beforeEmmaGetsIn = thisNight.plusMinutes 20
    billUpdate = new Note(beforeEmmaGetsIn, "This is great !")
    concert.update(bill, billUpdate)

    def mike = new Attendee(new Person(2L, "Mike"))
    mike.attend concert
    beforeEmmaGetsIn = thisNight.plusMinutes 25
    mikeUpdate = new Note(beforeEmmaGetsIn, "So much fun !")
    concert.update(mike, mikeUpdate)
}

When(~/^Emma attends it$/) { ->
    emma = new Attendee(new Person(3L, "Emma"))
    concert.attendee emma
}

Then(~/^she gets the updates of other attendees about it$/) { ->
    def emmaStream = PersonStream.of(emma)
    assert emmaStream.contains(billUpdate)
    assert emmaStream.contains(mikeUpdate)
}

