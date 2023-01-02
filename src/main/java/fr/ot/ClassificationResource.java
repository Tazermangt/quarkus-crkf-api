package fr.ot;

import fr.ot.entities.ClassificationEntity;
import fr.ot.repository.ClassificationRepository;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/classifications")
@Tag(name = "Classifications")
@Produces (MediaType.APPLICATION_JSON)
public class ClassificationResource {

    @Inject
    ClassificationRepository classificationRepository;

    @GET
    public Response getAll(){
        List<ClassificationEntity> classifications = classificationRepository.listAll();
        return Response.ok(classifications).build();
    }
}
