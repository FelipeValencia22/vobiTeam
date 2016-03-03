package com.vobi.team.presentation.backingBeans;

import com.vobi.team.exceptions.*;
import com.vobi.team.modelo.*;
import com.vobi.team.modelo.dto.VtUsuarioArtefactoDTO;
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
public class VtUsuarioArtefactoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(VtUsuarioArtefactoView.class);
    private InputText txtActivo;
    private InputText txtUsuCreador;
    private InputText txtUsuModificador;
    private InputText txtArteCodigo_VtArtefacto;
    private InputText txtInteCodigo_VtInteres;
    private InputText txtUsuaCodigo_VtUsuario;
    private InputText txtUsuartCodigo;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<VtUsuarioArtefactoDTO> data;
    private VtUsuarioArtefactoDTO selectedVtUsuarioArtefacto;
    private VtUsuarioArtefacto entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public VtUsuarioArtefactoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            VtUsuarioArtefactoDTO vtUsuarioArtefactoDTO = (VtUsuarioArtefactoDTO) e.getObject();

            if (txtActivo == null) {
                txtActivo = new InputText();
            }

            txtActivo.setValue(vtUsuarioArtefactoDTO.getActivo());

            if (txtUsuCreador == null) {
                txtUsuCreador = new InputText();
            }

            txtUsuCreador.setValue(vtUsuarioArtefactoDTO.getUsuCreador());

            if (txtUsuModificador == null) {
                txtUsuModificador = new InputText();
            }

            txtUsuModificador.setValue(vtUsuarioArtefactoDTO.getUsuModificador());

            if (txtArteCodigo_VtArtefacto == null) {
                txtArteCodigo_VtArtefacto = new InputText();
            }

            txtArteCodigo_VtArtefacto.setValue(vtUsuarioArtefactoDTO.getArteCodigo_VtArtefacto());

            if (txtInteCodigo_VtInteres == null) {
                txtInteCodigo_VtInteres = new InputText();
            }

            txtInteCodigo_VtInteres.setValue(vtUsuarioArtefactoDTO.getInteCodigo_VtInteres());

            if (txtUsuaCodigo_VtUsuario == null) {
                txtUsuaCodigo_VtUsuario = new InputText();
            }

            txtUsuaCodigo_VtUsuario.setValue(vtUsuarioArtefactoDTO.getUsuaCodigo_VtUsuario());

            if (txtUsuartCodigo == null) {
                txtUsuartCodigo = new InputText();
            }

            txtUsuartCodigo.setValue(vtUsuarioArtefactoDTO.getUsuartCodigo());

            if (txtFechaCreacion == null) {
                txtFechaCreacion = new Calendar();
            }

            txtFechaCreacion.setValue(vtUsuarioArtefactoDTO.getFechaCreacion());

            if (txtFechaModificacion == null) {
                txtFechaModificacion = new Calendar();
            }

            txtFechaModificacion.setValue(vtUsuarioArtefactoDTO.getFechaModificacion());

            Long usuartCodigo = FacesUtils.checkLong(txtUsuartCodigo);
            entity = businessDelegatorView.getVtUsuarioArtefacto(usuartCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedVtUsuarioArtefacto = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedVtUsuarioArtefacto = null;

        if (txtActivo != null) {
            txtActivo.setValue(null);
            txtActivo.setDisabled(true);
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

        if (txtInteCodigo_VtInteres != null) {
            txtInteCodigo_VtInteres.setValue(null);
            txtInteCodigo_VtInteres.setDisabled(true);
        }

        if (txtUsuaCodigo_VtUsuario != null) {
            txtUsuaCodigo_VtUsuario.setValue(null);
            txtUsuaCodigo_VtUsuario.setDisabled(true);
        }

        if (txtFechaCreacion != null) {
            txtFechaCreacion.setValue(null);
            txtFechaCreacion.setDisabled(true);
        }

        if (txtFechaModificacion != null) {
            txtFechaModificacion.setValue(null);
            txtFechaModificacion.setDisabled(true);
        }

        if (txtUsuartCodigo != null) {
            txtUsuartCodigo.setValue(null);
            txtUsuartCodigo.setDisabled(false);
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
            Long usuartCodigo = FacesUtils.checkLong(txtUsuartCodigo);
            entity = (usuartCodigo != null)
                ? businessDelegatorView.getVtUsuarioArtefacto(usuartCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtActivo.setDisabled(false);
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setDisabled(false);
            txtArteCodigo_VtArtefacto.setDisabled(false);
            txtInteCodigo_VtInteres.setDisabled(false);
            txtUsuaCodigo_VtUsuario.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setDisabled(false);
            txtUsuartCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtActivo.setValue(entity.getActivo());
            txtActivo.setDisabled(false);
            txtFechaCreacion.setValue(entity.getFechaCreacion());
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setValue(entity.getFechaModificacion());
            txtFechaModificacion.setDisabled(false);
            txtUsuCreador.setValue(entity.getUsuCreador());
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setValue(entity.getUsuModificador());
            txtUsuModificador.setDisabled(false);
            txtArteCodigo_VtArtefacto.setValue(entity.getVtArtefacto()
                                                     .getArteCodigo());
            txtArteCodigo_VtArtefacto.setDisabled(false);
            txtInteCodigo_VtInteres.setValue(entity.getVtInteres()
                                                   .getInteCodigo());
            txtInteCodigo_VtInteres.setDisabled(false);
            txtUsuaCodigo_VtUsuario.setValue(entity.getVtUsuario()
                                                   .getUsuaCodigo());
            txtUsuaCodigo_VtUsuario.setDisabled(false);
            txtUsuartCodigo.setValue(entity.getUsuartCodigo());
            txtUsuartCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedVtUsuarioArtefacto = (VtUsuarioArtefactoDTO) (evt.getComponent()
                                                                 .getAttributes()
                                                                 .get("selectedVtUsuarioArtefacto"));
        txtActivo.setValue(selectedVtUsuarioArtefacto.getActivo());
        txtActivo.setDisabled(false);
        txtFechaCreacion.setValue(selectedVtUsuarioArtefacto.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaModificacion.setValue(selectedVtUsuarioArtefacto.getFechaModificacion());
        txtFechaModificacion.setDisabled(false);
        txtUsuCreador.setValue(selectedVtUsuarioArtefacto.getUsuCreador());
        txtUsuCreador.setDisabled(false);
        txtUsuModificador.setValue(selectedVtUsuarioArtefacto.getUsuModificador());
        txtUsuModificador.setDisabled(false);
        txtArteCodigo_VtArtefacto.setValue(selectedVtUsuarioArtefacto.getArteCodigo_VtArtefacto());
        txtArteCodigo_VtArtefacto.setDisabled(false);
        txtInteCodigo_VtInteres.setValue(selectedVtUsuarioArtefacto.getInteCodigo_VtInteres());
        txtInteCodigo_VtInteres.setDisabled(false);
        txtUsuaCodigo_VtUsuario.setValue(selectedVtUsuarioArtefacto.getUsuaCodigo_VtUsuario());
        txtUsuaCodigo_VtUsuario.setDisabled(false);
        txtUsuartCodigo.setValue(selectedVtUsuarioArtefacto.getUsuartCodigo());
        txtUsuartCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedVtUsuarioArtefacto == null) && (entity == null)) {
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
            entity = new VtUsuarioArtefacto();

            Long usuartCodigo = FacesUtils.checkLong(txtUsuartCodigo);

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setUsuartCodigo(usuartCodigo);
            entity.setVtArtefacto((FacesUtils.checkLong(
                    txtArteCodigo_VtArtefacto) != null)
                ? businessDelegatorView.getVtArtefacto(FacesUtils.checkLong(
                        txtArteCodigo_VtArtefacto)) : null);
            entity.setVtInteres((FacesUtils.checkLong(txtInteCodigo_VtInteres) != null)
                ? businessDelegatorView.getVtInteres(FacesUtils.checkLong(
                        txtInteCodigo_VtInteres)) : null);
            entity.setVtUsuario((FacesUtils.checkLong(txtUsuaCodigo_VtUsuario) != null)
                ? businessDelegatorView.getVtUsuario(FacesUtils.checkLong(
                        txtUsuaCodigo_VtUsuario)) : null);
            businessDelegatorView.saveVtUsuarioArtefacto(entity);
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
                Long usuartCodigo = new Long(selectedVtUsuarioArtefacto.getUsuartCodigo());
                entity = businessDelegatorView.getVtUsuarioArtefacto(usuartCodigo);
            }

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setVtArtefacto((FacesUtils.checkLong(
                    txtArteCodigo_VtArtefacto) != null)
                ? businessDelegatorView.getVtArtefacto(FacesUtils.checkLong(
                        txtArteCodigo_VtArtefacto)) : null);
            entity.setVtInteres((FacesUtils.checkLong(txtInteCodigo_VtInteres) != null)
                ? businessDelegatorView.getVtInteres(FacesUtils.checkLong(
                        txtInteCodigo_VtInteres)) : null);
            entity.setVtUsuario((FacesUtils.checkLong(txtUsuaCodigo_VtUsuario) != null)
                ? businessDelegatorView.getVtUsuario(FacesUtils.checkLong(
                        txtUsuaCodigo_VtUsuario)) : null);
            businessDelegatorView.updateVtUsuarioArtefacto(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedVtUsuarioArtefacto = (VtUsuarioArtefactoDTO) (evt.getComponent()
                                                                     .getAttributes()
                                                                     .get("selectedVtUsuarioArtefacto"));

            Long usuartCodigo = new Long(selectedVtUsuarioArtefacto.getUsuartCodigo());
            entity = businessDelegatorView.getVtUsuarioArtefacto(usuartCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long usuartCodigo = FacesUtils.checkLong(txtUsuartCodigo);
            entity = businessDelegatorView.getVtUsuarioArtefacto(usuartCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteVtUsuarioArtefacto(entity);
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
            selectedVtUsuarioArtefacto = (VtUsuarioArtefactoDTO) (evt.getComponent()
                                                                     .getAttributes()
                                                                     .get("selectedVtUsuarioArtefacto"));

            Long usuartCodigo = new Long(selectedVtUsuarioArtefacto.getUsuartCodigo());
            entity = businessDelegatorView.getVtUsuarioArtefacto(usuartCodigo);
            businessDelegatorView.deleteVtUsuarioArtefacto(entity);
            data.remove(selectedVtUsuarioArtefacto);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String activo, Date fechaCreacion,
        Date fechaModificacion, Long usuCreador, Long usuModificador,
        Long usuartCodigo, Long arteCodigo_VtArtefacto,
        Long inteCodigo_VtInteres, Long usuaCodigo_VtUsuario)
        throws Exception {
        try {
            entity.setActivo(FacesUtils.checkString(activo));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(fechaModificacion));
            entity.setUsuCreador(FacesUtils.checkLong(usuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(usuModificador));
            businessDelegatorView.updateVtUsuarioArtefacto(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("VtUsuarioArtefactoView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtActivo() {
        return txtActivo;
    }

    public void setTxtActivo(InputText txtActivo) {
        this.txtActivo = txtActivo;
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

    public InputText getTxtInteCodigo_VtInteres() {
        return txtInteCodigo_VtInteres;
    }

    public void setTxtInteCodigo_VtInteres(InputText txtInteCodigo_VtInteres) {
        this.txtInteCodigo_VtInteres = txtInteCodigo_VtInteres;
    }

    public InputText getTxtUsuaCodigo_VtUsuario() {
        return txtUsuaCodigo_VtUsuario;
    }

    public void setTxtUsuaCodigo_VtUsuario(InputText txtUsuaCodigo_VtUsuario) {
        this.txtUsuaCodigo_VtUsuario = txtUsuaCodigo_VtUsuario;
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

    public InputText getTxtUsuartCodigo() {
        return txtUsuartCodigo;
    }

    public void setTxtUsuartCodigo(InputText txtUsuartCodigo) {
        this.txtUsuartCodigo = txtUsuartCodigo;
    }

    public List<VtUsuarioArtefactoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataVtUsuarioArtefacto();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<VtUsuarioArtefactoDTO> vtUsuarioArtefactoDTO) {
        this.data = vtUsuarioArtefactoDTO;
    }

    public VtUsuarioArtefactoDTO getSelectedVtUsuarioArtefacto() {
        return selectedVtUsuarioArtefacto;
    }

    public void setSelectedVtUsuarioArtefacto(
        VtUsuarioArtefactoDTO vtUsuarioArtefacto) {
        this.selectedVtUsuarioArtefacto = vtUsuarioArtefacto;
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
