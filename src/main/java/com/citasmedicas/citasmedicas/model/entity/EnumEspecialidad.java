package com.citasmedicas.citasmedicas.model.entity;

public enum EnumEspecialidad {
    MEDICINA_GENERAL("Medicina General"),
    CARDIOLOGIA("cardiologia"),
    MEDICINA_INTERNA("medicina interna"),
    DERMATOLOGIA("Dermatologia"),
    REHABILITACION_FISICA("Rehabilitacion fisica"),
    PSICOLOGIA("psicologia"),
    ODONTOLOGIA("odontologia"),
    RADIOLOGIA("Radiologia");

    private String descripcion;

    private EnumEspecialidad( String descripcion){
        this.descripcion=descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getNombreEnum(){
        return this.name();
    }

}
