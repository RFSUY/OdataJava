package com.example.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.communication.ODataClientErrorException;
import org.apache.olingo.client.api.communication.request.cud.ODataEntityCreateRequest;
import org.apache.olingo.client.api.communication.request.retrieve.ODataEntitySetIteratorRequest;
import org.apache.olingo.client.api.communication.response.ODataEntityCreateResponse;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.api.domain.ClientEntitySet;
import org.apache.olingo.client.api.domain.ClientEntitySetIterator;
import org.apache.olingo.commons.api.format.ContentType;
import org.apache.olingo.commons.api.http.HttpHeader;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import com.example.Util;
import com.example.Model.FinancialDimensionValues;
import com.example.Model.PurchaseOrder;
import com.example.Service.PrintOutService;
import com.example.Service.TokenService;

public class OdataController {

    com.example.Util Util = new Util();
    PrintOutService printOut = new PrintOutService();
    TokenService TokenService = new TokenService();

    public void getFinancialDimensionValues(String entity, String financialDimension)
            throws ClientProtocolException, IOException {
        try {
            ODataClient client = Util.getClient();
            URI absoluteUri;
            if (entity.equalsIgnoreCase("")) {
                absoluteUri = Util.getUriToPath(Util.getClient(), financialDimension);
            } else {
                absoluteUri = Util.getUriToMethod(client, entity, financialDimension);
            }

            ODataEntitySetIteratorRequest<ClientEntitySet, ClientEntity> request = client.getRetrieveRequestFactory()
                    .getEntitySetIteratorRequest(absoluteUri);

            request.addCustomHeader(HttpHeader.AUTHORIZATION, "Bearer " + TokenService.getBearerToken());
            request.setAccept("application/json;odata.metadata=minimal");

            ODataRetrieveResponse<ClientEntitySetIterator<ClientEntitySet, ClientEntity>> response = request.execute();
            ClientEntitySetIterator<ClientEntitySet, ClientEntity> iterator = response.getBody();

            printOut.FinancialDimensionValue(iterator);

        } catch (ODataClientErrorException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void getLastIdVendorInvoiceHeader(String entity, String filter, String orderBy)
            throws ClientProtocolException, IOException {
        try {
            ODataClient client = Util.getClient();
            URI absoluteUri = Util.getUriLastRegister(client, entity, filter, orderBy);
            
            ODataEntitySetIteratorRequest<ClientEntitySet, ClientEntity> request = client.getRetrieveRequestFactory()
                    .getEntitySetIteratorRequest(absoluteUri);

            request.addCustomHeader(HttpHeader.AUTHORIZATION, "Bearer " + TokenService.getBearerToken());
            request.setAccept("application/json;odata.metadata=minimal");

            ODataRetrieveResponse<ClientEntitySetIterator<ClientEntitySet, ClientEntity>> response = request.execute();
            ClientEntitySetIterator<ClientEntitySet, ClientEntity> iterator = response.getBody();

            printOut.LastIdInvoice(iterator);

        } catch (ODataClientErrorException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void getTotalInvoiceToExpand(String PurchaseOrder)
            throws URISyntaxException, ClientProtocolException, IOException {

        ODataClient client = Util.getClient();
        URI absoluteUri = Util.getUriExpand(client, PurchaseOrder);

        ODataEntitySetIteratorRequest<ClientEntitySet, ClientEntity> request = client.getRetrieveRequestFactory()
                .getEntitySetIteratorRequest(absoluteUri);

        request.addCustomHeader(HttpHeader.AUTHORIZATION, "Bearer " + TokenService.getBearerToken());
        request.setAccept("application/json;odata.metadata=minimal");

        ODataRetrieveResponse<ClientEntitySetIterator<ClientEntitySet, ClientEntity>> response = request.execute();
        ClientEntitySetIterator<ClientEntitySet, ClientEntity> iterator = response.getBody();

        printOut.TotalInvoiceToExpand(iterator);
    }

    public void CreateFinancialDimension() {

        FinancialDimensionValues financialDimensionValues = new FinancialDimensionValues();
        ODataClient client = Util.getClient();

        try {
            // Crear entidad a enviar
            ClientEntity entityToCreate = financialDimensionValues.FinancialDimensionValuesToClientEntity(client);

            URI serviceUri = Util.getAbsolutUri(client);
            ODataEntityCreateRequest<ClientEntity> request = client.getCUDRequestFactory().getEntityCreateRequest(
                    serviceUri, entityToCreate);
            request.addCustomHeader(HttpHeader.AUTHORIZATION, "Bearer " + TokenService.getBearerToken());
            request.setFormat(ContentType.JSON);

            ODataEntityCreateResponse<ClientEntity> response = request.execute();

            if (response.getStatusCode() == HttpStatusCode.CREATED.getStatusCode()) {
                System.out.println("Objeto creado exitosamente");
            } else {
                System.out.println("Error al crear el objeto");
            }
        } catch (ODataClientErrorException e) {
            System.out.println("Error al crear el objeto: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void getTotalInvoiceToService() throws ClientProtocolException, IOException {

        ODataClient client = Util.getClient();
        PurchaseOrder PO = new PurchaseOrder();
        try {

            ClientEntity entityToCreate = PO.NumberPurchaseOrderToClientEntity(client);

            URI serviceUri = Util.getAbsolutUriService(client);
            ODataEntityCreateRequest<ClientEntity> request = client.getCUDRequestFactory().getEntityCreateRequest(
                    serviceUri, entityToCreate);
            request.addCustomHeader(HttpHeader.AUTHORIZATION, "Bearer " + TokenService.getBearerToken());

            ODataEntityCreateResponse<ClientEntity> response = request.execute();

            InputStream clientEntity = response.getRawResponse();
            System.out.println("Total: " + IOUtils.toString(clientEntity, StandardCharsets.UTF_8));

        } catch (ODataClientErrorException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
