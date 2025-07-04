package ma.fm6education.adhesion.repository;

import ma.fm6education.adhesion.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    
    List<Document> findByApplicationId(Long applicationId);
    
    List<Document> findByDocumentType(String documentType);
    
    List<Document> findByApplicationIdAndDocumentType(Long applicationId, String documentType);
} 