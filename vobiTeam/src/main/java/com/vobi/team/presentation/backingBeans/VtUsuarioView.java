package com.vobi.team.presentation.backingBeans;

import com.vobi.team.exceptions.*;
import com.vobi.team.modelo.*;
import com.vobi.team.modelo.dto.VtUsuarioDTO;
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
public class VtUsuarioView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(VtUsuarioView.class);
    private InputText txtActivo;
    private InputText txtClave;
    private InputText txtLogin;
    private InputText txtNombre;
    private InputText txtUsuCreador;
    private InputText txtUsuModificador;
    private InputText txtEmprCodigo_VtEmpresa;
    private InputText txtUsuaCodigo;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<VtUsuarioDTO> data;
    private VtUsuarioDTO selectedVtUsuario;
    private VtUsuario entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public VtUsuarioView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            VtUsuarioDTO vtUsuarioDTO = (VtUsuarioDTO) e.getObject();

            if (txtActivo == null) {
                txtActivo = new InputText();
            }

            txtActivo.setValue(vtUsuarioDTO.getActivo());

            if (txtClave == null) {
                txtClave = new InputText();
            }

            txtClave.setValue(vtUsuarioDTO.getClave());

            if (txtLogin == null) {
                txtLogin = new InputText();
            }

            txtLogin.setValue(vtUsuarioDTO.getLogin());

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(vtUsuarioDTO.getNombre());

            if (txtUsuCreador == null) {
                txtUsuCreador = new InputText();
            }

            txtUsuCreador.setValue(vtUsuarioDTO.getUsuCreador());

            if (txtUsuModificador == null) {
                txtUsuModificador = new InputText();
            }

            txtUsuModificador.setValue(vtUsuarioDTO.getUsuModificador());

            if (txtEmprCodigo_VtEmpresa == null) {
                txtEmprCodigo_VtEmpresa = new InputText();
            }

            txtEmprCodigo_VtEmpresa.setValue(vtUsuarioDTO.getEmprCodigo_VtEmpresa());

            if (txtUsuaCodigo == null) {
                txtUsuaCodigo = new InputText();
            }

            txtUsuaCodigo.setValue(vtUsuarioDTO.getUsuaCodigo());

            if (txtFechaCreacion == null) {
                txtFechaCreacion = new Calendar();
            }

            txtFechaCreacion.setValue(vtUsuarioDTO.getFechaCreacion());

            if (txtFechaModificacion == null) {
                txtFechaModificacion = new Calendar();
            }

            txtFechaModificacion.setValue(vtUsuarioDTO.getFechaModificacion());

            Long usuaCodigo = FacesUtils.checkLong(txtUsuaCodigo);
            entity = businessDelegatorView.getVtUsuario(usuaCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedVtUsuario = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedVtUsuario = null;

        if (txtActivo != null) {
            txtActivo.setValue(null);
            txtActivo.setDisabled(true);
        }

        if (txtClave != null) {
            txtClave.setValue(null);
            txtClave.setDisabled(true);
        }

        if (txtLogin != null) {
            txtLogin.setValue(null);
            txtLogin.setDisabled(true);
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

        if (txtEmprCodigo_VtEmpresa != null) {
            txtEmprCodigo_VtEmpresa.setValue(null);
            txtEmprCodigo_VtEmpresa.setDisabled(true);
        }

        if (txtFechaCreacion != null) {
            txtFechaCreacion.setValue(null);
            txtFechaCreacion.setDisabled(true);
        }

        if (txtFechaModificacion != null) {
            txtFechaModificacion.setValue(null);
            txtFechaModificacion.setDisabled(true);
        }

        if (txtUsuaCodigo != null) {
            txtUsuaCodigo.setValue(null);
            txtUsuaCodigo.setDisabled(false);
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
            Long usuaCodigo = FacesUtils.checkLong(txtUsuaCodigo);
            entity = (usuaCodigo != null)
                ? businessDelegatorView.getVtUsuario(usuaCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtActivo.setDisabled(false);
            txtClave.setDisabled(false);
            txtLogin.setDisabled(false);
            txtNombre.setDisabled(false);
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setDisabled(false);
            txtEmprCodigo_VtEmpresa.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setDisabled(false);
            txtUsuaCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtActivo.setValue(entity.getActivo());
            txtActivo.setDisabled(false);
            txtClave.setValue(entity.getClave());
            txtClave.setDisabled(false);
            txtFechaCreacion.setValue(entity.getFechaCreacion());
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setValue(entity.getFechaModificacion());
            txtFechaModificacion.setDisabled(false);
            txtLogin.setValue(entity.getLogin());
            txtLogin.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtUsuCreador.setValue(entity.getUsuCreador());
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setValue(entity.getUsuModificador());
            txtUsuModificador.setDisabled(false);
            txtEmprCodigo_VtEmpresa.setValue(entity.getVtEmpresa()
                                                   .getEmprCodigo());
            txtEmprCodigo_VtEmpresa.setDisabled(false);
            txtUsuaCodigo.setValue(entity.getUsuaCodigo());
            txtUsuaCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedVtUsuario = (VtUsuarioDTO) (evt.getComponent().getAttributes()
                                               .get("selectedVtUsuario"));
        txtActivo.setValue(selectedVtUsuario.getActivo());
        txtActivo.setDisabled(false);
        txtClave.setValue(selectedVtUsuario.getClave());
        txtClave.setDisabled(false);
        txtFechaCreacion.setValue(selectedVtUsuario.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaModificacion.setValue(selectedVtUsuario.getFechaModificacion());
        txtFechaModificacion.setDisabled(false);
        txtLogin.setValue(selectedVtUsuario.getLogin());
        txtLogin.setDisabled(false);
        txtNombre.setValue(selectedVtUsuario.getNombre());
        txtNombre.setDisabled(false);
        txtUsuCreador.setValue(selectedVtUsuario.getUsuCreador());
        txtUsuCreador.setDisabled(false);
        txtUsuModificador.setValue(selectedVtUsuario.getUsuModificador());
        txtUsuModificador.setDisabled(false);
        txtEmprCodigo_VtEmpresa.setValue(selectedVtUsuario.getEmprCodigo_VtEmpresa());
        txtEmprCodigo_VtEmpresa.setDisabled(false);
        txtUsuaCodigo.setValue(selectedVtUsuario.getUsuaCodigo());
        txtUsuaCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedVtUsuario == null) && (entity == null)) {
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
            entity = new VtUsuario();

            Long usuaCodigo = FacesUtils.checkLong(txtUsuaCodigo);

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setClave(FacesUtils.checkString(txtClave));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setLogin(FacesUtils.checkString(txtLogin));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setUsuaCodigo(usuaCodigo);
            entity.setVtEmpresa((FacesUtils.checkLong(txtEmprCodigo_VtEmpresa) != null)
                ? businessDelegatorView.getVtEmpresa(FacesUtils.checkLong(
                        txtEmprCodigo_VtEmpresa)) : null);
            businessDelegatorView.saveVtUsuario(entity);
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
                Long usuaCodigo = new Long(selectedVtUsuario.getUsuaCodigo());
                entity = businessDelegatorView.getVtUsuario(usuaCodigo);
            }

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setClave(FacesUtils.checkString(txtClave));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setLogin(FacesUtils.checkString(txtLogin));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setVtEmpresa((FacesUtils.checkLong(txtEmprCodigo_VtEmpresa) != null)
                ? businessDelegatorView.getVtEmpresa(FacesUtils.checkLong(
                        txtEmprCodigo_VtEmpresa)) : null);
            businessDelegatorView.updateVtUsuario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedVtUsuario = (VtUsuarioDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedVtUsuario"));

            Long usuaCodigo = new Long(selectedVtUsuario.getUsuaCodigo());
            entity = businessDelegatorView.getVtUsuario(usuaCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long usuaCodigo = FacesUtils.checkLong(txtUsuaCodigo);
            entity = businessDelegatorView.getVtUsuario(usuaCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteVtUsuario(entity);
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
            selectedVtUsuario = (VtUsuarioDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedVtUsuario"));

            Long usuaCodigo = new Long(selectedVtUsuario.getUsuaCodigo());
            entity = businessDelegatorView.getVtUsuario(usuaCodigo);
            businessDelegatorView.deleteVtUsuario(entity);
            data.remove(selectedVtUsuario);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String activo, String clave,
        Date fechaCreacion, Date fechaModificacion, String login,
        String nombre, Long usuCreador, Long usuModificador, Long usuaCodigo,
        Long emprCodigo_VtEmpresa) throws Exception {
        try {
            entity.setActivo(FacesUtils.checkString(activo));
            entity.setClave(FacesUtils.checkString(clave));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(fechaModificacion));
            entity.setLogin(FacesUtils.checkString(login));
            entity.setNombre(FacesUtils.checkString(nombre));
            entity.setUsuCreador(FacesUtils.checkLong(usuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(usuModificador));
            businessDelegatorView.updateVtUsuario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("VtUsuarioView").requestRender();
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

    public InputText getTxtClave() {
        return txtClave;
    }

    public void setTxtClave(InputText txtClave) {
        this.txtClave = txtClave;
    }

    public InputText getTxtLogin() {
        return txtLogin;
    }

    public void setTxtLogin(InputText txtLogin) {
        this.txtLogin = txtLogin;
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

    public InputText getTxtEmprCodigo_VtEmpresa() {
        return txtEmprCodigo_VtEmpresa;
    }

    public void setTxtEmprCodigo_VtEmpresa(InputText txtEmprCodigo_VtEmpresa) {
        this.txtEmprCodigo_VtEmpresa = txtEmprCodigo_VtEmpresa;
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

    public InputText getTxtUsuaCodigo() {
        return txtUsuaCodigo;
    }

    public void setTxtUsuaCodigo(InputText txtUsuaCodigo) {
        this.txtUsuaCodigo = txtUsuaCodigo;
    }

    public List<VtUsuarioDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataVtUsuario();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<VtUsuarioDTO> vtUsuarioDTO) {
        this.data = vtUsuarioDTO;
    }

    public VtUsuarioDTO getSelectedVtUsuario() {
        return selectedVtUsuario;
    }

    public void setSelectedVtUsuario(VtUsuarioDTO vtUsuario) {
        this.selectedVtUsuario = vtUsuario;
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
