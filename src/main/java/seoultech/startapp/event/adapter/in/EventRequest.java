package seoultech.startapp.event.adapter.in;

import seoultech.startapp.event.application.port.in.EventCommand;

import java.time.LocalDateTime;

record EventRequest(String title, String formLink, String imageUrl, LocalDateTime startTime, LocalDateTime endTime) {

    public EventCommand ToEventCommand() {
        return EventCommand.builder()
                           .title(title)
                           .formLink(formLink)
                           .imageUrl(imageUrl)
                           .startTime(startTime)
                           .endTime(endTime)
                           .build();
    }

}
