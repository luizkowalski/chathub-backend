package com.inkdrop.application.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.inkdrop.domain.models.Message;
import com.inkdrop.infrastructure.annotations.Command;
import lombok.extern.slf4j.Slf4j;

@Command
@Slf4j
public class PushToFirebaseCommand {

  public void pushToFirebase(Message message) {
    try {
      DatabaseReference db = getDatabase(message);
      db.child(message.getId().toString()).setValue(new ObjectMapper().writeValueAsString(message));
    } catch (Exception e) {
      log.error("Could not send message to Firebase", e);
    }
  }

  private DatabaseReference getDatabase(Message message) {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference db = database.getReference("/messages");
    return db.child(message.getRoom().getUid().toString());
  }
}
