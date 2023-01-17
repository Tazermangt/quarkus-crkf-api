package fr.ot;

import fr.ot.entities.ClassificationEntity;
import fr.ot.repository.ClassificationRepository;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
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
    public Response getAllClassifications(){
        List<ClassificationEntity> classifications = classificationRepository.listAll();
        return Response.ok(classifications).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Integer id){
        ClassificationEntity classification = classificationRepository.findById(id);
        if(classification == null){
            return Response.noContent().build();
        }else{
            return Response.ok(classification).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(ClassificationEntity classification){
        if(classification == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        classificationRepository.persist(classification);
        return Response.status(204).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(ClassificationEntity classification){
        if(classification == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        classificationRepository.update("classification = ?1 where id_classification=?2", classification.getClassification(), classification.getIdClassification());
        return Response.status(204).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Integer id){
        if(id == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        classificationRepository.delete("id_classification = ?1", id);
        return Response.status(204).build();
    }
}
