package com.vobi.team.presentation.backingBeans;

import com.vobi.team.exceptions.*;
import com.vobi.team.modelo.*;
import com.vobi.team.modelo.dto.VtUsuarioRolDTO;
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
public class VtUsuarioRolView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(VtUsuarioRolView.class);
    private InputText txtActivo;
    private InputText txtUsuCreador;
    private InputText txtUsuModificador;
    private InputText txtRolCodigo_VtRol;
    private InputText txtUsuaCodigo_VtUsuario;
    private InputText txtUsroCodigo;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<VtUsuarioRolDTO> data;
    private VtUsuarioRolDTO selectedVtUsuarioRol;
    private VtUsuarioRol entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public VtUsuarioRolView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            VtUsuarioRolDTO vtUsuarioRolDTO = (VtUsuarioRolDTO) e.getObject();

            if (txtActivo == null) {
                txtActivo = new InputText();
            }

            txtActivo.setValue(vtUsuarioRolDTO.getActivo());

            if (txtUsuCreador == null) {
                txtUsuCreador = new InputText();
            }

            txtUsuCreador.setValue(vtUsuarioRolDTO.getUsuCreador());

            if (txtUsuModificador == null) {
                txtUsuModificador = new InputText();
            }

            txtUsuModificador.setValue(vtUsuarioRolDTO.getUsuModificador());

            if (txtRolCodigo_VtRol == null) {
                txtRolCodigo_VtRol = new InputText();
            }

            txtRolCodigo_VtRol.setValue(vtUsuarioRolDTO.getRolCodigo_VtRol());

            if (txtUsuaCodigo_VtUsuario == null) {
                txtUsuaCodigo_VtUsuario = new InputText();
            }

            txtUsuaCodigo_VtUsuario.setValue(vtUsuarioRolDTO.getUsuaCodigo_VtUsuario());

            if (txtUsroCodigo == null) {
                txtUsroCodigo = new InputText();
            }

            txtUsroCodigo.setValue(vtUsuarioRolDTO.getUsroCodigo());

            if (txtFechaCreacion == null) {
                txtFechaCreacion = new Calendar();
            }

            txtFechaCreacion.setValue(vtUsuarioRolDTO.getFechaCreacion());

            if (txtFechaModificacion == null) {
                txtFechaModificacion = new Calendar();
            }

            txtFechaModificacion.setValue(vtUsuarioRolDTO.getFechaModificacion());

            Long usroCodigo = FacesUtils.checkLong(txtUsroCodigo);
            entity = businessDelegatorView.getVtUsuarioRol(usroCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedVtUsuarioRol = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedVtUsuarioRol = null;

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

        if (txtRolCodigo_VtRol != null) {
            txtRolCodigo_VtRol.setValue(null);
            txtRolCodigo_VtRol.setDisabled(true);
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

        if (txtUsroCodigo != null) {
            txtUsroCodigo.setValue(null);
            txtUsroCodigo.setDisabled(false);
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
            Long usroCodigo = FacesUtils.checkLong(txtUsroCodigo);
            entity = (usroCodigo != null)
                ? businessDelegatorView.getVtUsuarioRol(usroCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtActivo.setDisabled(false);
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setDisabled(false);
            txtRolCodigo_VtRol.setDisabled(false);
            txtUsuaCodigo_VtUsuario.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setDisabled(false);
            txtUsroCodigo.setDisabled(false);
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
            txtRolCodigo_VtRol.setValue(entity.getVtRol().getRolCodigo());
            txtRolCodigo_VtRol.setDisabled(false);
            txtUsuaCodigo_VtUsuario.setValue(entity.getVtUsuario()
                                                   .getUsuaCodigo());
            txtUsuaCodigo_VtUsuario.setDisabled(false);
            txtUsroCodigo.setValue(entity.getUsroCodigo());
            txtUsroCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedVtUsuarioRol = (VtUsuarioRolDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedVtUsuarioRol"));
        txtActivo.setValue(selectedVtUsuarioRol.getActivo());
        txtActivo.setDisabled(false);
        txtFechaCreacion.setValue(selectedVtUsuarioRol.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaModificacion.setValue(selectedVtUsuarioRol.getFechaModificacion());
        txtFechaModificacion.setDisabled(false);
        txtUsuCreador.setValue(selectedVtUsuarioRol.getUsuCreador());
        txtUsuCreador.setDisabled(false);
        txtUsuModificador.setValue(selectedVtUsuarioRol.getUsuModificador());
        txtUsuModificador.setDisabled(false);
        txtRolCodigo_VtRol.setValue(selectedVtUsuarioRol.getRolCodigo_VtRol());
        txtRolCodigo_VtRol.setDisabled(false);
        txtUsuaCodigo_VtUsuario.setValue(selectedVtUsuarioRol.getUsuaCodigo_VtUsuario());
        txtUsuaCodigo_VtUsuario.setDisabled(false);
        txtUsroCodigo.setValue(selectedVtUsuarioRol.getUsroCodigo());
        txtUsroCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedVtUsuarioRol == null) && (entity == null)) {
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
            entity = new VtUsuarioRol();

            Long usroCodigo = FacesUtils.checkLong(txtUsroCodigo);

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setUsroCodigo(usroCodigo);
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setVtRol((FacesUtils.checkLong(txtRolCodigo_VtRol) != null)
                ? businessDelegatorView.getVtRol(FacesUtils.checkLong(
                        txtRolCodigo_VtRol)) : null);
            entity.setVtUsuario((FacesUtils.checkLong(txtUsuaCodigo_VtUsuario) != null)
                ? businessDelegatorView.getVtUsuario(FacesUtils.checkLong(
                        txtUsuaCodigo_VtUsuario)) : null);
            businessDelegatorView.saveVtUsuarioRol(entity);
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
                Long usroCodigo = new Long(selectedVtUsuarioRol.getUsroCodigo());
                entity = businessDelegatorView.getVtUsuarioRol(usroCodigo);
            }

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setVtRol((FacesUtils.checkLong(txtRolCodigo_VtRol) != null)
                ? businessDelegatorView.getVtRol(FacesUtils.checkLong(
                        txtRolCodigo_VtRol)) : null);
            entity.setVtUsuario((FacesUtils.checkLong(txtUsuaCodigo_VtUsuario) != null)
                ? businessDelegatorView.getVtUsuario(FacesUtils.checkLong(
                        txtUsuaCodigo_VtUsuario)) : null);
            businessDelegatorView.updateVtUsuarioRol(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedVtUsuarioRol = (VtUsuarioRolDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedVtUsuarioRol"));

            Long usroCodigo = new Long(selectedVtUsuarioRol.getUsroCodigo());
            entity = businessDelegatorView.getVtUsuarioRol(usroCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long usroCodigo = FacesUtils.checkLong(txtUsroCodigo);
            entity = businessDelegatorView.getVtUsuarioRol(usroCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteVtUsuarioRol(entity);
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
            selectedVtUsuarioRol = (VtUsuarioRolDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedVtUsuarioRol"));

            Long usroCodigo = new Long(selectedVtUsuarioRol.getUsroCodigo());
            entity = businessDelegatorView.getVtUsuarioRol(usroCodigo);
            businessDelegatorView.deleteVtUsuarioRol(entity);
            data.remove(selectedVtUsuarioRol);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String activo, Date fechaCreacion,
        Date fechaModificacion, Long usroCodigo, Long usuCreador,
        Long usuModificador, Long rolCodigo_VtRol, Long usuaCodigo_VtUsuario)
        throws Exception {
        try {
            entity.setActivo(FacesUtils.checkString(activo));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(fechaModificacion));
            entity.setUsuCreador(FacesUtils.checkLong(usuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(usuModificador));
            businessDelegatorView.updateVtUsuarioRol(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("VtUsuarioRolView").requestRender();
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

    public InputText getTxtRolCodigo_VtRol() {
        return txtRolCodigo_VtRol;
    }

    public void setTxtRolCodigo_VtRol(InputText txtRolCodigo_VtRol) {
        this.txtRolCodigo_VtRol = txtRolCodigo_VtRol;
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

    public InputText getTxtUsroCodigo() {
        return txtUsroCodigo;
    }

    public void setTxtUsroCodigo(InputText txtUsroCodigo) {
        this.txtUsroCodigo = txtUsroCodigo;
    }

    public List<VtUsuarioRolDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataVtUsuarioRol();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<VtUsuarioRolDTO> vtUsuarioRolDTO) {
        this.data = vtUsuarioRolDTO;
    }

    public VtUsuarioRolDTO getSelectedVtUsuarioRol() {
        return selectedVtUsuarioRol;
    }

    public void setSelectedVtUsuarioRol(VtUsuarioRolDTO vtUsuarioRol) {
        this.selectedVtUsuarioRol = vtUsuarioRol;
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
