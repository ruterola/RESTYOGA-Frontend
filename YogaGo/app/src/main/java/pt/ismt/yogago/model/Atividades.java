package pt.ismt.yogago.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Atividades {
    private int id;
    private int userId;
    private int planoId;
    @SerializedName("data_start")
    private Date dataStart;
    @SerializedName("data_fin")
    private Date dataFin;
    private Planos planos;

    public Planos getPlanos() {
        return planos;
    }

    public void setPlanos(Planos planos) {
        this.planos = planos;
    }

    public Atividades(int id, int userId, int planoId, Date dataStart, Date dataFin) {
        this.id = id;
        this.userId = userId;
        this.planoId = planoId;
        this.dataStart = dataStart;
        this.dataFin = dataFin;
    }

    public Atividades() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPlanoId() {
        return planoId;
    }

    public void setPlanoId(int planoId) {
        this.planoId = planoId;
    }

    public Date getDataStart() {
        return dataStart;
    }

    public void setDataStart(Date dataStart) {
        this.dataStart = dataStart;
    }

    public Date getDataFin() {
        return dataFin;
    }

    public void setDataFin(Date dataFin) {
        this.dataFin = dataFin;
    }
}
