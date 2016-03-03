package com.vobi.team.presentation.backingBeans;

import com.vobi.team.exceptions.*;
import com.vobi.team.modelo.*;
import com.vobi.team.modelo.dto.VtProyectoDTO;
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
public class VtProyectoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(VtProyectoView.class);
    private InputText txtActivo;
    private InputText txtDescripcion;
    private InputText txtNombre;
    private InputText txtPublico;
    private InputText txtUsuCreador;
    private InputText txtUsuModificador;
    private InputText txtEmprCodigo_VtEmpresa;
    private InputText txtProyCodigo;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<VtProyectoDTO> data;
    private VtProyectoDTO selectedVtProyecto;
    private VtProyecto entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public VtProyectoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            VtProyectoDTO vtProyectoDTO = (VtProyectoDTO) e.getObject();

            if (txtActivo == null) {
                txtActivo = new InputText();
            }

            txtActivo.setValue(vtProyectoDTO.getActivo());

            if (txtDescripcion == null) {
                txtDescripcion = new InputText();
            }

            txtDescripcion.setValue(vtProyectoDTO.getDescripcion());

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(vtProyectoDTO.getNombre());

            if (txtPublico == null) {
                txtPublico = new InputText();
            }

            txtPublico.setValue(vtProyectoDTO.getPublico());

            if (txtUsuCreador == null) {
                txtUsuCreador = new InputText();
            }

            txtUsuCreador.setValue(vtProyectoDTO.getUsuCreador());

            if (txtUsuModificador == null) {
                txtUsuModificador = new InputText();
            }

            txtUsuModificador.setValue(vtProyectoDTO.getUsuModificador());

            if (txtEmprCodigo_VtEmpresa == null) {
                txtEmprCodigo_VtEmpresa = new InputText();
            }

            txtEmprCodigo_VtEmpresa.setValue(vtProyectoDTO.getEmprCodigo_VtEmpresa());

            if (txtProyCodigo == null) {
                txtProyCodigo = new InputText();
            }

            txtProyCodigo.setValue(vtProyectoDTO.getProyCodigo());

            if (txtFechaCreacion == null) {
                txtFechaCreacion = new Calendar();
            }

            txtFechaCreacion.setValue(vtProyectoDTO.getFechaCreacion());

            if (txtFechaModificacion == null) {
                txtFechaModificacion = new Calendar();
            }

            txtFechaModificacion.setValue(vtProyectoDTO.getFechaModificacion());

            Long proyCodigo = FacesUtils.checkLong(txtProyCodigo);
            entity = businessDelegatorView.getVtProyecto(proyCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedVtProyecto = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedVtProyecto = null;

        if (txtActivo != null) {
            txtActivo.setValue(null);
            txtActivo.setDisabled(true);
        }

        if (txtDescripcion != null) {
            txtDescripcion.setValue(null);
            txtDescripcion.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
            txtNombre.setDisabled(true);
        }

        if (txtPublico != null) {
            txtPublico.setValue(null);
            txtPublico.setDisabled(true);
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

        if (txtProyCodigo != null) {
            txtProyCodigo.setValue(null);
            txtProyCodigo.setDisabled(false);
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
            Long proyCodigo = FacesUtils.checkLong(txtProyCodigo);
            entity = (proyCodigo != null)
                ? businessDelegatorView.getVtProyecto(proyCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtActivo.setDisabled(false);
            txtDescripcion.setDisabled(false);
            txtNombre.setDisabled(false);
            txtPublico.setDisabled(false);
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setDisabled(false);
            txtEmprCodigo_VtEmpresa.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setDisabled(false);
            txtProyCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtActivo.setValue(entity.getActivo());
            txtActivo.setDisabled(false);
            txtDescripcion.setValue(entity.getDescripcion());
            txtDescripcion.setDisabled(false);
            txtFechaCreacion.setValue(entity.getFechaCreacion());
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setValue(entity.getFechaModificacion());
            txtFechaModificacion.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtPublico.setValue(entity.getPublico());
            txtPublico.setDisabled(false);
            txtUsuCreador.setValue(entity.getUsuCreador());
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setValue(entity.getUsuModificador());
            txtUsuModificador.setDisabled(false);
            txtEmprCodigo_VtEmpresa.setValue(entity.getVtEmpresa()
                                                   .getEmprCodigo());
            txtEmprCodigo_VtEmpresa.setDisabled(false);
            txtProyCodigo.setValue(entity.getProyCodigo());
            txtProyCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedVtProyecto = (VtProyectoDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedVtProyecto"));
        txtActivo.setValue(selectedVtProyecto.getActivo());
        txtActivo.setDisabled(false);
        txtDescripcion.setValue(selectedVtProyecto.getDescripcion());
        txtDescripcion.setDisabled(false);
        txtFechaCreacion.setValue(selectedVtProyecto.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaModificacion.setValue(selectedVtProyecto.getFechaModificacion());
        txtFechaModificacion.setDisabled(false);
        txtNombre.setValue(selectedVtProyecto.getNombre());
        txtNombre.setDisabled(false);
        txtPublico.setValue(selectedVtProyecto.getPublico());
        txtPublico.setDisabled(false);
        txtUsuCreador.setValue(selectedVtProyecto.getUsuCreador());
        txtUsuCreador.setDisabled(false);
        txtUsuModificador.setValue(selectedVtProyecto.getUsuModificador());
        txtUsuModificador.setDisabled(false);
        txtEmprCodigo_VtEmpresa.setValue(selectedVtProyecto.getEmprCodigo_VtEmpresa());
        txtEmprCodigo_VtEmpresa.setDisabled(false);
        txtProyCodigo.setValue(selectedVtProyecto.getProyCodigo());
        txtProyCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedVtProyecto == null) && (entity == null)) {
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
            entity = new VtProyecto();

            Long proyCodigo = FacesUtils.checkLong(txtProyCodigo);

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setProyCodigo(proyCodigo);
            entity.setPublico(FacesUtils.checkString(txtPublico));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setVtEmpresa((FacesUtils.checkLong(txtEmprCodigo_VtEmpresa) != null)
                ? businessDelegatorView.getVtEmpresa(FacesUtils.checkLong(
                        txtEmprCodigo_VtEmpresa)) : null);
            businessDelegatorView.saveVtProyecto(entity);
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
                Long proyCodigo = new Long(selectedVtProyecto.getProyCodigo());
                entity = businessDelegatorView.getVtProyecto(proyCodigo);
            }

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setPublico(FacesUtils.checkString(txtPublico));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setVtEmpresa((FacesUtils.checkLong(txtEmprCodigo_VtEmpresa) != null)
                ? businessDelegatorView.getVtEmpresa(FacesUtils.checkLong(
                        txtEmprCodigo_VtEmpresa)) : null);
            businessDelegatorView.updateVtProyecto(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedVtProyecto = (VtProyectoDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedVtProyecto"));

            Long proyCodigo = new Long(selectedVtProyecto.getProyCodigo());
            entity = businessDelegatorView.getVtProyecto(proyCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long proyCodigo = FacesUtils.checkLong(txtProyCodigo);
            entity = businessDelegatorView.getVtProyecto(proyCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteVtProyecto(entity);
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
            selectedVtProyecto = (VtProyectoDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedVtProyecto"));

            Long proyCodigo = new Long(selectedVtProyecto.getProyCodigo());
            entity = businessDelegatorView.getVtProyecto(proyCodigo);
            businessDelegatorView.deleteVtProyecto(entity);
            data.remove(selectedVtProyecto);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String activo, String descripcion,
        Date fechaCreacion, Date fechaModificacion, String nombre,
        Long proyCodigo, String publico, Long usuCreador, Long usuModificador,
        Long emprCodigo_VtEmpresa) throws Exception {
        try {
            entity.setActivo(FacesUtils.checkString(activo));
            entity.setDescripcion(FacesUtils.checkString(descripcion));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(fechaModificacion));
            entity.setNombre(FacesUtils.checkString(nombre));
            entity.setPublico(FacesUtils.checkString(publico));
            entity.setUsuCreador(FacesUtils.checkLong(usuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(usuModificador));
            businessDelegatorView.updateVtProyecto(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("VtProyectoView").requestRender();
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

    public InputText getTxtDescripcion() {
        return txtDescripcion;
    }

    public void setTxtDescripcion(InputText txtDescripcion) {
        this.txtDescripcion = txtDescripcion;
    }

    public InputText getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(InputText txtNombre) {
        this.txtNombre = txtNombre;
    }

    public InputText getTxtPublico() {
        return txtPublico;
    }

    public void setTxtPublico(InputText txtPublico) {
        this.txtPublico = txtPublico;
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

    public InputText getTxtProyCodigo() {
        return txtProyCodigo;
    }

    public void setTxtProyCodigo(InputText txtProyCodigo) {
        this.txtProyCodigo = txtProyCodigo;
    }

    public List<VtProyectoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataVtProyecto();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<VtProyectoDTO> vtProyectoDTO) {
        this.data = vtProyectoDTO;
    }

    public VtProyectoDTO getSelectedVtProyecto() {
        return selectedVtProyecto;
    }

    public void setSelectedVtProyecto(VtProyectoDTO vtProyecto) {
        this.selectedVtProyecto = vtProyecto;
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
