package com.example.Model;

import java.util.ArrayList;
import java.util.List;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.api.domain.ClientProperty;
import org.apache.olingo.commons.api.edm.FullQualifiedName;

public class FinancialDimensionValues {

    private String FinancialDimension;
    private String LegalEntityId;
    private String DimensionValue;
    private String GroupDimension;
    private String IsBlockedForManualEntry;
    private String IsSuspended;
    private String IsTotal;
    private String Description;
    private String ActiveFrom;
    private String ActiveTo;
    private String Owner;
    private String IsBalancing_PSN;

    public String getFinancialDimension() {
        return FinancialDimension;
    }

    public void setFinancialDimension(String financialDimension) {
        FinancialDimension = financialDimension;
    }

    public String getLegalEntityId() {
        return LegalEntityId;
    }

    public void setLegalEntityId(String legalEntityId) {
        LegalEntityId = legalEntityId;
    }

    public String getDimensionValue() {
        return DimensionValue;
    }

    public void setDimensionValue(String dimensionValue) {
        DimensionValue = dimensionValue;
    }

    public String getGroupDimension() {
        return GroupDimension;
    }

    public void setGroupDimension(String groupDimension) {
        GroupDimension = groupDimension;
    }

    public String getIsBlockedForManualEntry() {
        return IsBlockedForManualEntry;
    }

    public void setIsBlockedForManualEntry(String isBlockedForManualEntry) {
        IsBlockedForManualEntry = isBlockedForManualEntry;
    }

    public String getIsSuspended() {
        return IsSuspended;
    }

    public void setIsSuspended(String isSuspended) {
        IsSuspended = isSuspended;
    }

    public String getIsTotal() {
        return IsTotal;
    }

    public void setIsTotal(String isTotal) {
        IsTotal = isTotal;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getActiveFrom() {
        return ActiveFrom;
    }

    public void setActiveFrom(String activeFrom) {
        ActiveFrom = activeFrom;
    }

    public String getActiveTo() {
        return ActiveTo;
    }

    public void setActiveTo(String activeTo) {
        ActiveTo = activeTo;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public String getIsBalancing_PSN() {
        return IsBalancing_PSN;
    }

    public void setIsBalancing_PSN(String isBalancing_PSN) {
        IsBalancing_PSN = isBalancing_PSN;
    }

    public FinancialDimensionValues createdFinalDimesion(){
        FinancialDimensionValues financialDimension = new FinancialDimensionValues();
        financialDimension.setFinancialDimension("AFE");
        financialDimension.setDimensionValue("ADMIFINA");
        financialDimension.setGroupDimension("");
        financialDimension.setIsBlockedForManualEntry("No");
        financialDimension.setIsSuspended("No");
        financialDimension.setIsTotal("No");
        financialDimension.setDescription("TestCreate");
        financialDimension.setActiveFrom("1900-01-01T12:00:00Z");
        financialDimension.setActiveTo("1900-01-01T12:00:00Z");;
        financialDimension.setOwner("");
        financialDimension.setIsBalancing_PSN("No");

        return financialDimension;
    }

    public ClientEntity FinancialDimensionValuesToClientEntity(ODataClient odataClient) {

        FinancialDimensionValues financialDimension = createdFinalDimesion();

        ClientEntity entityToCreate = odataClient.getObjectFactory()
                .newEntity(new FullQualifiedName("FinancialDimensionValues.FinancialDimensionValues"));

        List<ClientProperty> properties = new ArrayList<ClientProperty>();

        properties.add(odataClient.getObjectFactory().newPrimitiveProperty("FinancialDimension",
                odataClient.getObjectFactory().newPrimitiveValueBuilder()
                        .buildString(financialDimension.getFinancialDimension())));

        properties.add(odataClient.getObjectFactory().newPrimitiveProperty("DimensionValue",
                odataClient.getObjectFactory().newPrimitiveValueBuilder()
                        .buildString(financialDimension.getDimensionValue())));

        properties.add(odataClient.getObjectFactory().newPrimitiveProperty("GroupDimension",
                odataClient.getObjectFactory().newPrimitiveValueBuilder()
                        .buildString(financialDimension.getGroupDimension())));

        properties.add(odataClient.getObjectFactory().newPrimitiveProperty("IsBlockedForManualEntry",
                odataClient.getObjectFactory().newPrimitiveValueBuilder()
                        .buildString(financialDimension.getIsBlockedForManualEntry())));

        properties.add(odataClient.getObjectFactory().newPrimitiveProperty("IsSuspended",
                odataClient.getObjectFactory().newPrimitiveValueBuilder()
                        .buildString(financialDimension.getIsSuspended())));

        properties.add(odataClient.getObjectFactory().newPrimitiveProperty("IsTotal",
                odataClient.getObjectFactory().newPrimitiveValueBuilder()
                        .buildString(financialDimension.getIsTotal())));

        properties.add(odataClient.getObjectFactory().newPrimitiveProperty("Description",
                odataClient.getObjectFactory().newPrimitiveValueBuilder()
                        .buildString(financialDimension.getDescription())));

        properties.add(odataClient.getObjectFactory().newPrimitiveProperty("ActiveFrom",
                odataClient.getObjectFactory().newPrimitiveValueBuilder()
                        .buildString(financialDimension.getActiveFrom())));

        properties.add(odataClient.getObjectFactory().newPrimitiveProperty("ActiveTo",
                odataClient.getObjectFactory().newPrimitiveValueBuilder()
                        .buildString(financialDimension.getActiveTo())));

        properties.add(odataClient.getObjectFactory().newPrimitiveProperty("Owner",
                odataClient.getObjectFactory().newPrimitiveValueBuilder()
                        .buildString(financialDimension.getOwner())));

        properties.add(odataClient.getObjectFactory().newPrimitiveProperty("IsBalancing_PSN",
                odataClient.getObjectFactory().newPrimitiveValueBuilder()
                        .buildString(financialDimension.getIsBalancing_PSN())));

        entityToCreate.getProperties().addAll(properties);

        return entityToCreate;
    }
}
