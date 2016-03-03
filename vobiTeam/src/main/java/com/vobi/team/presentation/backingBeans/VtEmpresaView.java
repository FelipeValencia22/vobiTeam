package com.vobi.team.presentation.backingBeans;

import com.vobi.team.exceptions.*;
import com.vobi.team.modelo.*;
import com.vobi.team.modelo.dto.VtEmpresaDTO;
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
public class VtEmpresaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(VtEmpresaView.class);
    private InputText txtActivo;
    private InputText txtIdentificacion;
    private InputText txtNombre;
    private InputText txtUsuCreador;
    private InputText txtUsuModificador;
    private InputText txtEmprCodigo;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<VtEmpresaDTO> data;
    private VtEmpresaDTO selectedVtEmpresa;
    private VtEmpresa entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public VtEmpresaView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            VtEmpresaDTO vtEmpresaDTO = (VtEmpresaDTO) e.getObject();

            if (txtActivo == null) {
                txtActivo = new InputText();
            }

            txtActivo.setValue(vtEmpresaDTO.getActivo());

            if (txtIdentificacion == null) {
                txtIdentificacion = new InputText();
            }

            txtIdentificacion.setValue(vtEmpresaDTO.getIdentificacion());

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(vtEmpresaDTO.getNombre());

            if (txtUsuCreador == null) {
                txtUsuCreador = new InputText();
            }

            txtUsuCreador.setValue(vtEmpresaDTO.getUsuCreador());

            if (txtUsuModificador == null) {
                txtUsuModificador = new InputText();
            }

            txtUsuModificador.setValue(vtEmpresaDTO.getUsuModificador());

            if (txtEmprCodigo == null) {
                txtEmprCodigo = new InputText();
            }

            txtEmprCodigo.setValue(vtEmpresaDTO.getEmprCodigo());

            if (txtFechaCreacion == null) {
                txtFechaCreacion = new Calendar();
            }

            txtFechaCreacion.setValue(vtEmpresaDTO.getFechaCreacion());

            if (txtFechaModificacion == null) {
                txtFechaModificacion = new Calendar();
            }

            txtFechaModificacion.setValue(vtEmpresaDTO.getFechaModificacion());

            Long emprCodigo = FacesUtils.checkLong(txtEmprCodigo);
            entity = businessDelegatorView.getVtEmpresa(emprCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedVtEmpresa = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedVtEmpresa = null;

        if (txtActivo != null) {
            txtActivo.setValue(null);
            txtActivo.setDisabled(true);
        }

        if (txtIdentificacion != null) {
            txtIdentificacion.setValue(null);
            txtIdentificacion.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
            txtNombre.setDisabled(true);
        }

        if (txtUsuCreador != null) {
            txtUsuCreador.setValue(null);
            txtUsuCreador.setDisabled(true);
        }

        if (txtUsuModificador != null) {
            txtUsuModificador.setValue(null);
            txtUsuModificador.setDisabled(true);
        }

        if (txtFechaCreacion != null) {
            txtFechaCreacion.setValue(null);
            txtFechaCreacion.setDisabled(true);
        }

        if (txtFechaModificacion != null) {
            txtFechaModificacion.setValue(null);
            txtFechaModificacion.setDisabled(true);
        }

        if (txtEmprCodigo != null) {
            txtEmprCodigo.setValue(null);
            txtEmprCodigo.setDisabled(false);
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
            Long emprCodigo = FacesUtils.checkLong(txtEmprCodigo);
            entity = (emprCodigo != null)
                ? businessDelegatorView.getVtEmpresa(emprCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtActivo.setDisabled(false);
            txtIdentificacion.setDisabled(false);
            txtNombre.setDisabled(false);
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setDisabled(false);
            txtEmprCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtActivo.setValue(entity.getActivo());
            txtActivo.setDisabled(false);
            txtFechaCreacion.setValue(entity.getFechaCreacion());
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setValue(entity.getFechaModificacion());
            txtFechaModificacion.setDisabled(false);
            txtIdentificacion.setValue(entity.getIdentificacion());
            txtIdentificacion.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtUsuCreador.setValue(entity.getUsuCreador());
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setValue(entity.getUsuModificador());
            txtUsuModificador.setDisabled(false);
            txtEmprCodigo.setValue(entity.getEmprCodigo());
            txtEmprCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedVtEmpresa = (VtEmpresaDTO) (evt.getComponent().getAttributes()
                                               .get("selectedVtEmpresa"));
        txtActivo.setValue(selectedVtEmpresa.getActivo());
        txtActivo.setDisabled(false);
        txtFechaCreacion.setValue(selectedVtEmpresa.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaModificacion.setValue(selectedVtEmpresa.getFechaModificacion());
        txtFechaModificacion.setDisabled(false);
        txtIdentificacion.setValue(selectedVtEmpresa.getIdentificacion());
        txtIdentificacion.setDisabled(false);
        txtNombre.setValue(selectedVtEmpresa.getNombre());
        txtNombre.setDisabled(false);
        txtUsuCreador.setValue(selectedVtEmpresa.getUsuCreador());
        txtUsuCreador.setDisabled(false);
        txtUsuModificador.setValue(selectedVtEmpresa.getUsuModificador());
        txtUsuModificador.setDisabled(false);
        txtEmprCodigo.setValue(selectedVtEmpresa.getEmprCodigo());
        txtEmprCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedVtEmpresa == null) && (entity == null)) {
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
            entity = new VtEmpresa();

            Long emprCodigo = FacesUtils.checkLong(txtEmprCodigo);

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setEmprCodigo(emprCodigo);
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setIdentificacion(FacesUtils.checkString(txtIdentificacion));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            businessDelegatorView.saveVtEmpresa(entity);
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
                Long emprCodigo = new Long(selectedVtEmpresa.getEmprCodigo());
                entity = businessDelegatorView.getVtEmpresa(emprCodigo);
            }

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setIdentificacion(FacesUtils.checkString(txtIdentificacion));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            businessDelegatorView.updateVtEmpresa(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedVtEmpresa = (VtEmpresaDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedVtEmpresa"));

            Long emprCodigo = new Long(selectedVtEmpresa.getEmprCodigo());
            entity = businessDelegatorView.getVtEmpresa(emprCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long emprCodigo = FacesUtils.checkLong(txtEmprCodigo);
            entity = businessDelegatorView.getVtEmpresa(emprCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteVtEmpresa(entity);
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
            selectedVtEmpresa = (VtEmpresaDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedVtEmpresa"));

            Long emprCodigo = new Long(selectedVtEmpresa.getEmprCodigo());
            entity = businessDelegatorView.getVtEmpresa(emprCodigo);
            businessDelegatorView.deleteVtEmpresa(entity);
            data.remove(selectedVtEmpresa);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String activo, Long emprCodigo,
        Date fechaCreacion, Date fechaModificacion, String identificacion,
        String nombre, Long usuCreador, Long usuModificador)
        throws Exception {
        try {
            entity.setActivo(FacesUtils.checkString(activo));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(fechaModificacion));
            entity.setIdentificacion(FacesUtils.checkString(identificacion));
            entity.setNombre(FacesUtils.checkString(nombre));
            entity.setUsuCreador(FacesUtils.checkLong(usuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(usuModificador));
            businessDelegatorView.updateVtEmpresa(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("VtEmpresaView").requestRender();
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

    public InputText getTxtIdentificacion() {
        return txtIdentificacion;
    }

    public void setTxtIdentificacion(InputText txtIdentificacion) {
        this.txtIdentificacion = txtIdentificacion;
    }

    public InputText getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(InputText txtNombre) {
        this.txtNombre = txtNombre;
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

    public InputText getTxtEmprCodigo() {
        return txtEmprCodigo;
    }

    public void setTxtEmprCodigo(InputText txtEmprCodigo) {
        this.txtEmprCodigo = txtEmprCodigo;
    }

    public List<VtEmpresaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataVtEmpresa();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<VtEmpresaDTO> vtEmpresaDTO) {
        this.data = vtEmpresaDTO;
    }

    public VtEmpresaDTO getSelectedVtEmpresa() {
        return selectedVtEmpresa;
    }

    public void setSelectedVtEmpresa(VtEmpresaDTO vtEmpresa) {
        this.selectedVtEmpresa = vtEmpresa;
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
