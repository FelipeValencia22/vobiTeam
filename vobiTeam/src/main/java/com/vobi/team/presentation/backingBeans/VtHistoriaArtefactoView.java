package com.vobi.team.presentation.backingBeans;

import com.vobi.team.exceptions.*;
import com.vobi.team.modelo.*;
import com.vobi.team.modelo.dto.VtHistoriaArtefactoDTO;
import com.vobi.team.presentation.businessDelegate.*;
import com.vobi.team.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

import org.primefaces.event.RowEditEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


/**
 * @author Zathura Code Generator http://zathuracode.org/
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class VtHistoriaArtefactoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(VtHistoriaArtefactoView.class);
    private InputText txtDescripcion;
    private InputText txtEsfuerzoEstimado;
    private InputText txtEsfuerzoReal;
    private InputText txtEsfuerzoRestante;
    private InputText txtOrigen;
    private InputText txtPuntos;
    private InputText txtTitulo;
    private InputText txtUsuCreador;
    private InputText txtUsuModificador;
    private InputText txtArteCodigo_VtArtefacto;
    private InputText txtHistoriaCodigo;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<VtHistoriaArtefactoDTO> data;
    private VtHistoriaArtefactoDTO selectedVtHistoriaArtefacto;
    private VtHistoriaArtefacto entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public VtHistoriaArtefactoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            VtHistoriaArtefactoDTO vtHistoriaArtefactoDTO = (VtHistoriaArtefactoDTO) e.getObject();

            if (txtDescripcion == null) {
                txtDescripcion = new InputText();
            }

            txtDescripcion.setValue(vtHistoriaArtefactoDTO.getDescripcion());

            if (txtEsfuerzoEstimado == null) {
                txtEsfuerzoEstimado = new InputText();
            }

            txtEsfuerzoEstimado.setValue(vtHistoriaArtefactoDTO.getEsfuerzoEstimado());

            if (txtEsfuerzoReal == null) {
                txtEsfuerzoReal = new InputText();
            }

            txtEsfuerzoReal.setValue(vtHistoriaArtefactoDTO.getEsfuerzoReal());

            if (txtEsfuerzoRestante == null) {
                txtEsfuerzoRestante = new InputText();
            }

            txtEsfuerzoRestante.setValue(vtHistoriaArtefactoDTO.getEsfuerzoRestante());

            if (txtOrigen == null) {
                txtOrigen = new InputText();
            }

            txtOrigen.setValue(vtHistoriaArtefactoDTO.getOrigen());

            if (txtPuntos == null) {
                txtPuntos = new InputText();
            }

            txtPuntos.setValue(vtHistoriaArtefactoDTO.getPuntos());

            if (txtTitulo == null) {
                txtTitulo = new InputText();
            }

            txtTitulo.setValue(vtHistoriaArtefactoDTO.getTitulo());

            if (txtUsuCreador == null) {
                txtUsuCreador = new InputText();
            }

            txtUsuCreador.setValue(vtHistoriaArtefactoDTO.getUsuCreador());

            if (txtUsuModificador == null) {
                txtUsuModificador = new InputText();
            }

            txtUsuModificador.setValue(vtHistoriaArtefactoDTO.getUsuModificador());

            if (txtArteCodigo_VtArtefacto == null) {
                txtArteCodigo_VtArtefacto = new InputText();
            }

            txtArteCodigo_VtArtefacto.setValue(vtHistoriaArtefactoDTO.getArteCodigo_VtArtefacto());

            if (txtHistoriaCodigo == null) {
                txtHistoriaCodigo = new InputText();
            }

            txtHistoriaCodigo.setValue(vtHistoriaArtefactoDTO.getHistoriaCodigo());

            if (txtFechaCreacion == null) {
                txtFechaCreacion = new Calendar();
            }

            txtFechaCreacion.setValue(vtHistoriaArtefactoDTO.getFechaCreacion());

            if (txtFechaModificacion == null) {
                txtFechaModificacion = new Calendar();
            }

            txtFechaModificacion.setValue(vtHistoriaArtefactoDTO.getFechaModificacion());

            Long historiaCodigo = FacesUtils.checkLong(txtHistoriaCodigo);
            entity = businessDelegatorView.getVtHistoriaArtefacto(historiaCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedVtHistoriaArtefacto = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedVtHistoriaArtefacto = null;

        if (txtDescripcion != null) {
            txtDescripcion.setValue(null);
            txtDescripcion.setDisabled(true);
        }

        if (txtEsfuerzoEstimado != null) {
            txtEsfuerzoEstimado.setValue(null);
            txtEsfuerzoEstimado.setDisabled(true);
        }

        if (txtEsfuerzoReal != null) {
            txtEsfuerzoReal.setValue(null);
            txtEsfuerzoReal.setDisabled(true);
        }

        if (txtEsfuerzoRestante != null) {
            txtEsfuerzoRestante.setValue(null);
            txtEsfuerzoRestante.setDisabled(true);
        }

        if (txtOrigen != null) {
            txtOrigen.setValue(null);
            txtOrigen.setDisabled(true);
        }

        if (txtPuntos != null) {
            txtPuntos.setValue(null);
            txtPuntos.setDisabled(true);
        }

        if (txtTitulo != null) {
            txtTitulo.setValue(null);
            txtTitulo.setDisabled(true);
        }

        if (txtUsuCreador != null) {
            txtUsuCreador.setValue(null);
            txtUsuCreador.setDisabled(true);
        }

        if (txtUsuModificador != null) {
            txtUsuModificador.setValue(null);
            txtUsuModificador.setDisabled(true);
        }

        if (txtArteCodigo_VtArtefacto != null) {
            txtArteCodigo_VtArtefacto.setValue(null);
            txtArteCodigo_VtArtefacto.setDisabled(true);
        }

        if (txtFechaCreacion != null) {
            txtFechaCreacion.setValue(null);
            txtFechaCreacion.setDisabled(true);
        }

        if (txtFechaModificacion != null) {
            txtFechaModificacion.setValue(null);
            txtFechaModificacion.setDisabled(true);
        }

        if (txtHistoriaCodigo != null) {
            txtHistoriaCodigo.setValue(null);
            txtHistoriaCodigo.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtFechaCreacion() {
        Date inputDate = (Date) txtFechaCreacion.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtFechaModificacion() {
        Date inputDate = (Date) txtFechaModificacion.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Long historiaCodigo = FacesUtils.checkLong(txtHistoriaCodigo);
            entity = (historiaCodigo != null)
                ? businessDelegatorView.getVtHistoriaArtefacto(historiaCodigo)
                : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcion.setDisabled(false);
            txtEsfuerzoEstimado.setDisabled(false);
            txtEsfuerzoReal.setDisabled(false);
            txtEsfuerzoRestante.setDisabled(false);
            txtOrigen.setDisabled(false);
            txtPuntos.setDisabled(false);
            txtTitulo.setDisabled(false);
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setDisabled(false);
            txtArteCodigo_VtArtefacto.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setDisabled(false);
            txtHistoriaCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcion.setValue(entity.getDescripcion());
            txtDescripcion.setDisabled(false);
            txtEsfuerzoEstimado.setValue(entity.getEsfuerzoEstimado());
            txtEsfuerzoEstimado.setDisabled(false);
            txtEsfuerzoReal.setValue(entity.getEsfuerzoReal());
            txtEsfuerzoReal.setDisabled(false);
            txtEsfuerzoRestante.setValue(entity.getEsfuerzoRestante());
            txtEsfuerzoRestante.setDisabled(false);
            txtFechaCreacion.setValue(entity.getFechaCreacion());
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setValue(entity.getFechaModificacion());
            txtFechaModificacion.setDisabled(false);
            txtOrigen.setValue(entity.getOrigen());
            txtOrigen.setDisabled(false);
            txtPuntos.setValue(entity.getPuntos());
            txtPuntos.setDisabled(false);
            txtTitulo.setValue(entity.getTitulo());
            txtTitulo.setDisabled(false);
            txtUsuCreador.setValue(entity.getUsuCreador());
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setValue(entity.getUsuModificador());
            txtUsuModificador.setDisabled(false);
            txtArteCodigo_VtArtefacto.setValue(entity.getVtArtefacto()
                                                     .getArteCodigo());
            txtArteCodigo_VtArtefacto.setDisabled(false);
            txtHistoriaCodigo.setValue(entity.getHistoriaCodigo());
            txtHistoriaCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedVtHistoriaArtefacto = (VtHistoriaArtefactoDTO) (evt.getComponent()
                                                                   .getAttributes()
                                                                   .get("selectedVtHistoriaArtefacto"));
        txtDescripcion.setValue(selectedVtHistoriaArtefacto.getDescripcion());
        txtDescripcion.setDisabled(false);
        txtEsfuerzoEstimado.setValue(selectedVtHistoriaArtefacto.getEsfuerzoEstimado());
        txtEsfuerzoEstimado.setDisabled(false);
        txtEsfuerzoReal.setValue(selectedVtHistoriaArtefacto.getEsfuerzoReal());
        txtEsfuerzoReal.setDisabled(false);
        txtEsfuerzoRestante.setValue(selectedVtHistoriaArtefacto.getEsfuerzoRestante());
        txtEsfuerzoRestante.setDisabled(false);
        txtFechaCreacion.setValue(selectedVtHistoriaArtefacto.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaModificacion.setValue(selectedVtHistoriaArtefacto.getFechaModificacion());
        txtFechaModificacion.setDisabled(false);
        txtOrigen.setValue(selectedVtHistoriaArtefacto.getOrigen());
        txtOrigen.setDisabled(false);
        txtPuntos.setValue(selectedVtHistoriaArtefacto.getPuntos());
        txtPuntos.setDisabled(false);
        txtTitulo.setValue(selectedVtHistoriaArtefacto.getTitulo());
        txtTitulo.setDisabled(false);
        txtUsuCreador.setValue(selectedVtHistoriaArtefacto.getUsuCreador());
        txtUsuCreador.setDisabled(false);
        txtUsuModificador.setValue(selectedVtHistoriaArtefacto.getUsuModificador());
        txtUsuModificador.setDisabled(false);
        txtArteCodigo_VtArtefacto.setValue(selectedVtHistoriaArtefacto.getArteCodigo_VtArtefacto());
        txtArteCodigo_VtArtefacto.setDisabled(false);
        txtHistoriaCodigo.setValue(selectedVtHistoriaArtefacto.getHistoriaCodigo());
        txtHistoriaCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedVtHistoriaArtefacto == null) && (entity == null)) {
                action_create();
            } else {
                action_modify();
            }

            data = null;
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_create() {
        try {
            entity = new VtHistoriaArtefacto();

            Long historiaCodigo = FacesUtils.checkLong(txtHistoriaCodigo);

            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setEsfuerzoEstimado(FacesUtils.checkString(
                    txtEsfuerzoEstimado));
            entity.setEsfuerzoReal(FacesUtils.checkInteger(txtEsfuerzoReal));
            entity.setEsfuerzoRestante(FacesUtils.checkInteger(
                    txtEsfuerzoRestante));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setHistoriaCodigo(historiaCodigo);
            entity.setOrigen(FacesUtils.checkString(txtOrigen));
            entity.setPuntos(FacesUtils.checkInteger(txtPuntos));
            entity.setTitulo(FacesUtils.checkString(txtTitulo));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setVtArtefacto((FacesUtils.checkLong(
                    txtArteCodigo_VtArtefacto) != null)
                ? businessDelegatorView.getVtArtefacto(FacesUtils.checkLong(
                        txtArteCodigo_VtArtefacto)) : null);
            businessDelegatorView.saveVtHistoriaArtefacto(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
            action_clear();
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {
        try {
            if (entity == null) {
                Long historiaCodigo = new Long(selectedVtHistoriaArtefacto.getHistoriaCodigo());
                entity = businessDelegatorView.getVtHistoriaArtefacto(historiaCodigo);
            }

            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setEsfuerzoEstimado(FacesUtils.checkString(
                    txtEsfuerzoEstimado));
            entity.setEsfuerzoReal(FacesUtils.checkInteger(txtEsfuerzoReal));
            entity.setEsfuerzoRestante(FacesUtils.checkInteger(
                    txtEsfuerzoRestante));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setOrigen(FacesUtils.checkString(txtOrigen));
            entity.setPuntos(FacesUtils.checkInteger(txtPuntos));
            entity.setTitulo(FacesUtils.checkString(txtTitulo));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setVtArtefacto((FacesUtils.checkLong(
                    txtArteCodigo_VtArtefacto) != null)
                ? businessDelegatorView.getVtArtefacto(FacesUtils.checkLong(
                        txtArteCodigo_VtArtefacto)) : null);
            businessDelegatorView.updateVtHistoriaArtefacto(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedVtHistoriaArtefacto = (VtHistoriaArtefactoDTO) (evt.getComponent()
                                                                       .getAttributes()
                                                                       .get("selectedVtHistoriaArtefacto"));

            Long historiaCodigo = new Long(selectedVtHistoriaArtefacto.getHistoriaCodigo());
            entity = businessDelegatorView.getVtHistoriaArtefacto(historiaCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long historiaCodigo = FacesUtils.checkLong(txtHistoriaCodigo);
            entity = businessDelegatorView.getVtHistoriaArtefacto(historiaCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteVtHistoriaArtefacto(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
            data = null;
        } catch (Exception e) {
            throw e;
        }
    }

    public String action_closeDialog() {
        setShowDialog(false);
        action_clear();

        return "";
    }

    public String actionDeleteDataTableEditable(ActionEvent evt) {
        try {
            selectedVtHistoriaArtefacto = (VtHistoriaArtefactoDTO) (evt.getComponent()
                                                                       .getAttributes()
                                                                       .get("selectedVtHistoriaArtefacto"));

            Long historiaCodigo = new Long(selectedVtHistoriaArtefacto.getHistoriaCodigo());
            entity = businessDelegatorView.getVtHistoriaArtefacto(historiaCodigo);
            businessDelegatorView.deleteVtHistoriaArtefacto(entity);
            data.remove(selectedVtHistoriaArtefacto);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String descripcion,
        String esfuerzoEstimado, Integer esfuerzoReal,
        Integer esfuerzoRestante, Date fechaCreacion, Date fechaModificacion,
        Long historiaCodigo, String origen, Integer puntos, String titulo,
        Long usuCreador, Long usuModificador, Long arteCodigo_VtArtefacto)
        throws Exception {
        try {
            entity.setDescripcion(FacesUtils.checkString(descripcion));
            entity.setEsfuerzoEstimado(FacesUtils.checkString(esfuerzoEstimado));
            entity.setEsfuerzoReal(FacesUtils.checkInteger(esfuerzoReal));
            entity.setEsfuerzoRestante(FacesUtils.checkInteger(esfuerzoRestante));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(fechaModificacion));
            entity.setOrigen(FacesUtils.checkString(origen));
            entity.setPuntos(FacesUtils.checkInteger(puntos));
            entity.setTitulo(FacesUtils.checkString(titulo));
            entity.setUsuCreador(FacesUtils.checkLong(usuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(usuModificador));
            businessDelegatorView.updateVtHistoriaArtefacto(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("VtHistoriaArtefactoView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDescripcion() {
        return txtDescripcion;
    }

    public void setTxtDescripcion(InputText txtDescripcion) {
        this.txtDescripcion = txtDescripcion;
    }

    public InputText getTxtEsfuerzoEstimado() {
        return txtEsfuerzoEstimado;
    }

    public void setTxtEsfuerzoEstimado(InputText txtEsfuerzoEstimado) {
        this.txtEsfuerzoEstimado = txtEsfuerzoEstimado;
    }

    public InputText getTxtEsfuerzoReal() {
        return txtEsfuerzoReal;
    }

    public void setTxtEsfuerzoReal(InputText txtEsfuerzoReal) {
        this.txtEsfuerzoReal = txtEsfuerzoReal;
    }

    public InputText getTxtEsfuerzoRestante() {
        return txtEsfuerzoRestante;
    }

    public void setTxtEsfuerzoRestante(InputText txtEsfuerzoRestante) {
        this.txtEsfuerzoRestante = txtEsfuerzoRestante;
    }

    public InputText getTxtOrigen() {
        return txtOrigen;
    }

    public void setTxtOrigen(InputText txtOrigen) {
        this.txtOrigen = txtOrigen;
    }

    public InputText getTxtPuntos() {
        return txtPuntos;
    }

    public void setTxtPuntos(InputText txtPuntos) {
        this.txtPuntos = txtPuntos;
    }

    public InputText getTxtTitulo() {
        return txtTitulo;
    }

    public void setTxtTitulo(InputText txtTitulo) {
        this.txtTitulo = txtTitulo;
    }

    public InputText getTxtUsuCreador() {
        return txtUsuCreador;
    }

    public void setTxtUsuCreador(InputText txtUsuCreador) {
        this.txtUsuCreador = txtUsuCreador;
    }

    public InputText getTxtUsuModificador() {
        return txtUsuModificador;
    }

    public void setTxtUsuModificador(InputText txtUsuModificador) {
        this.txtUsuModificador = txtUsuModificador;
    }

    public InputText getTxtArteCodigo_VtArtefacto() {
        return txtArteCodigo_VtArtefacto;
    }

    public void setTxtArteCodigo_VtArtefacto(
        InputText txtArteCodigo_VtArtefacto) {
        this.txtArteCodigo_VtArtefacto = txtArteCodigo_VtArtefacto;
    }

    public Calendar getTxtFechaCreacion() {
        return txtFechaCreacion;
    }

    public void setTxtFechaCreacion(Calendar txtFechaCreacion) {
        this.txtFechaCreacion = txtFechaCreacion;
    }

    public Calendar getTxtFechaModificacion() {
        return txtFechaModificacion;
    }

    public void setTxtFechaModificacion(Calendar txtFechaModificacion) {
        this.txtFechaModificacion = txtFechaModificacion;
    }

    public InputText getTxtHistoriaCodigo() {
        return txtHistoriaCodigo;
    }

    public void setTxtHistoriaCodigo(InputText txtHistoriaCodigo) {
        this.txtHistoriaCodigo = txtHistoriaCodigo;
    }

    public List<VtHistoriaArtefactoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataVtHistoriaArtefacto();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<VtHistoriaArtefactoDTO> vtHistoriaArtefactoDTO) {
        this.data = vtHistoriaArtefactoDTO;
    }

    public VtHistoriaArtefactoDTO getSelectedVtHistoriaArtefacto() {
        return selectedVtHistoriaArtefacto;
    }

    public void setSelectedVtHistoriaArtefacto(
        VtHistoriaArtefactoDTO vtHistoriaArtefacto) {
        this.selectedVtHistoriaArtefacto = vtHistoriaArtefacto;
    }

    public CommandButton getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(CommandButton btnSave) {
        this.btnSave = btnSave;
    }

    public CommandButton getBtnModify() {
        return btnModify;
    }

    public void setBtnModify(CommandButton btnModify) {
        this.btnModify = btnModify;
    }

    public CommandButton getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(CommandButton btnDelete) {
        this.btnDelete = btnDelete;
    }

    public CommandButton getBtnClear() {
        return btnClear;
    }

    public void setBtnClear(CommandButton btnClear) {
        this.btnClear = btnClear;
    }

    public TimeZone getTimeZone() {
        return java.util.TimeZone.getDefault();
    }

    public IBusinessDelegatorView getBusinessDelegatorView() {
        return businessDelegatorView;
    }

    public void setBusinessDelegatorView(
        IBusinessDelegatorView businessDelegatorView) {
        this.businessDelegatorView = businessDelegatorView;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }
}
