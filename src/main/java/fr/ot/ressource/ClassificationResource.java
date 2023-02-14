package fr.ot.ressource;

import fr.ot.entities.ClassificationEntity;
import fr.ot.hateoas.HateOas;
import fr.ot.repository.ClassificationRepository;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.jboss.resteasy.annotations.Query;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/classifications")
@Tag(name = "Classifications")
@Produces(MediaType.APPLICATION_JSON)
public class ClassificationResource {

    @Inject
    ClassificationRepository classificationRepository;

    @GET
    public Response getAllClassifications(@Context UriInfo uriInfo) {
        List<ClassificationEntity> classifications = classificationRepository.listAll();
        if (!classifications.isEmpty()) {
            for (ClassificationEntity classificationEntity : classifications) {
                String uriBase = uriInfo.getRequestUriBuilder().build().toString();
                classificationEntity.addLink("all", uriBase);
                classificationEntity.addLink("self", uriBase + "/" + classificationEntity.getIdClassification());
            }
            return Response.ok(classifications).build();
        } else {
            return Response.noContent().build();
        }
    }

    @GET
    @Path("like/{classification}")
    public Response getLikeClassifications(@Context UriInfo uriInfo, @PathParam("classification") String classification){
        List<ClassificationEntity> classifications = classificationRepository.list("classification like CONCAT('%',?1,'%')",  classification );
        if (!classifications.isEmpty()) {
            for (ClassificationEntity classificationEntity : classifications) {
                String uriBase = uriInfo.getRequestUriBuilder().build().toString();
                classificationEntity.addLink("all", uriBase);
                classificationEntity.addLink("self", uriBase + "/" + classificationEntity.getIdClassification());
            }
            return Response.ok(classifications).build();
        } else {
            return Response.noContent().build();
        }
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        ClassificationEntity classification = classificationRepository.findById(id);
        if (classification != null) {
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            classification.addLink("all", uriBase.replace("/" + id, ""));
            classification.addLink("self", uriBase);
            return Response.ok(classification).build();
        } else {
            return Response.noContent().build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(ClassificationEntity classification, @Context UriInfo uriInfo) {
        if (classification != null) {
            classificationRepository.persist(classification);
            HateOas hateOas = new HateOas();
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            hateOas.addLink("all", uriBase);
            hateOas.addLink("self", uriBase + "/" + classification.getIdClassification());
            return Response.ok(hateOas).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(ClassificationEntity classification, @Context UriInfo uriInfo) {
        if (classification != null) {
            classificationRepository.update("classification = ?1 where id_classification=?2", classification.getClassification(), classification.getIdClassification());
            HateOas hateOas = new HateOas();
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            hateOas.addLink("all", uriBase);
            hateOas.addLink("self", uriBase + "/" + classification.getIdClassification());
            return Response.ok(hateOas).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        if (id != null) {
            classificationRepository.delete("id_classification = ?1", id);
            String uriBase = uriInfo.getRequestUriBuilder().build().toString();
            HateOas hateOas = new HateOas();
            hateOas.addLink("all", uriBase.replace("/" + id, ""));
            return Response.ok(hateOas).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
