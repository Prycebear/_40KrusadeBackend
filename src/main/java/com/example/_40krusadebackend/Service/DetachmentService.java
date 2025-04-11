package com.example._40krusadebackend.Service;

import com.example._40krusadebackend.Model.Detachment;
import com.example._40krusadebackend.Model.Faction;

import java.util.List;
import java.util.Optional;

public interface DetachmentService {
    Detachment createDetachment(Detachment detachment);
    Optional<Detachment> getDetachmentById(Long detachmentId);
    Optional<Detachment> getDetachmentByUsername(String detachmentName);
    List<Detachment> getAllDetachments();
    Detachment updateDetachment(Long detachmentId, Detachment detachmentName);
    void deleteDetachment(Long detachmentId);
}
