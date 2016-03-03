package com.vobi.team.presentation.backingBeans;

import com.vobi.team.exceptions.*;
import com.vobi.team.modelo.*;
import com.vobi.team.modelo.dto.VtPilaProductoDTO;
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
public class VtPilaProductoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(VtPilaProductoView.class);
    private InputText txtActivo;
    private InputText txtDescripcion;
    private InputText txtNombre;
    private InputText txtUsuCreador;
    private InputText txtUsuModificador;
    private InputText txtProyCodigo_VtProyecto;
    private InputText txtPilaCodigo;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<VtPilaProductoDTO> data;
    private VtPilaProductoDTO selectedVtPilaProducto;
    private VtPilaProducto entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public VtPilaProductoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            VtPilaProductoDTO vtPilaProductoDTO = (VtPilaProductoDTO) e.getObject();

            if (txtActivo == null) {
                txtActivo = new InputText();
            }

            txtActivo.setValue(vtPilaProductoDTO.getActivo());

            if (txtDescripcion == null) {
                txtDescripcion = new InputText();
            }

            txtDescripcion.setValue(vtPilaProductoDTO.getDescripcion());

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(vtPilaProductoDTO.getNombre());

            if (txtUsuCreador == null) {
                txtUsuCreador = new InputText();
            }

            txtUsuCreador.setValue(vtPilaProductoDTO.getUsuCreador());

            if (txtUsuModificador == null) {
                txtUsuModificador = new InputText();
            }

            txtUsuModificador.setValue(vtPilaProductoDTO.getUsuModificador());

            if (txtProyCodigo_VtProyecto == null) {
                txtProyCodigo_VtProyecto = new InputText();
            }

            txtProyCodigo_VtProyecto.setValue(vtPilaProductoDTO.getProyCodigo_VtProyecto());

            if (txtPilaCodigo == null) {
                txtPilaCodigo = new InputText();
            }

            txtPilaCodigo.setValue(vtPilaProductoDTO.getPilaCodigo());

            if (txtFechaCreacion == null) {
                txtFechaCreacion = new Calendar();
            }

            txtFechaCreacion.setValue(vtPilaProductoDTO.getFechaCreacion());

            if (txtFechaModificacion == null) {
                txtFechaModificacion = new Calendar();
            }

            txtFechaModificacion.setValue(vtPilaProductoDTO.getFechaModificacion());

            Long pilaCodigo = FacesUtils.checkLong(txtPilaCodigo);
            entity = businessDelegatorView.getVtPilaProducto(pilaCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedVtPilaProducto = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedVtPilaProducto = null;

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

        if (txtUsuCreador != null) {
            txtUsuCreador.setValue(null);
            txtUsuCreador.setDisabled(true);
        }

        if (txtUsuModificador != null) {
            txtUsuModificador.setValue(null);
            txtUsuModificador.setDisabled(true);
        }

        if (txtProyCodigo_VtProyecto != null) {
            txtProyCodigo_VtProyecto.setValue(null);
            txtProyCodigo_VtProyecto.setDisabled(true);
        }

        if (txtFechaCreacion != null) {
            txtFechaCreacion.setValue(null);
            txtFechaCreacion.setDisabled(true);
        }

        if (txtFechaModificacion != null) {
            txtFechaModificacion.setValue(null);
            txtFechaModificacion.setDisabled(true);
        }

        if (txtPilaCodigo != null) {
            txtPilaCodigo.setValue(null);
            txtPilaCodigo.setDisabled(false);
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
            Long pilaCodigo = FacesUtils.checkLong(txtPilaCodigo);
            entity = (pilaCodigo != null)
                ? businessDelegatorView.getVtPilaProducto(pilaCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtActivo.setDisabled(false);
            txtDescripcion.setDisabled(false);
            txtNombre.setDisabled(false);
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setDisabled(false);
            txtProyCodigo_VtProyecto.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setDisabled(false);
            txtPilaCodigo.setDisabled(false);
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
            txtUsuCreador.setValue(entity.getUsuCreador());
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setValue(entity.getUsuModificador());
            txtUsuModificador.setDisabled(false);
            txtProyCodigo_VtProyecto.setValue(entity.getVtProyecto()
                                                    .getProyCodigo());
            txtProyCodigo_VtProyecto.setDisabled(false);
            txtPilaCodigo.setValue(entity.getPilaCodigo());
            txtPilaCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedVtPilaProducto = (VtPilaProductoDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedVtPilaProducto"));
        txtActivo.setValue(selectedVtPilaProducto.getActivo());
        txtActivo.setDisabled(false);
        txtDescripcion.setValue(selectedVtPilaProducto.getDescripcion());
        txtDescripcion.setDisabled(false);
        txtFechaCreacion.setValue(selectedVtPilaProducto.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaModificacion.setValue(selectedVtPilaProducto.getFechaModificacion());
        txtFechaModificacion.setDisabled(false);
        txtNombre.setValue(selectedVtPilaProducto.getNombre());
        txtNombre.setDisabled(false);
        txtUsuCreador.setValue(selectedVtPilaProducto.getUsuCreador());
        txtUsuCreador.setDisabled(false);
        txtUsuModificador.setValue(selectedVtPilaProducto.getUsuModificador());
        txtUsuModificador.setDisabled(false);
        txtProyCodigo_VtProyecto.setValue(selectedVtPilaProducto.getProyCodigo_VtProyecto());
        txtProyCodigo_VtProyecto.setDisabled(false);
        txtPilaCodigo.setValue(selectedVtPilaProducto.getPilaCodigo());
        txtPilaCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedVtPilaProducto == null) && (entity == null)) {
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
            entity = new VtPilaProducto();

            Long pilaCodigo = FacesUtils.checkLong(txtPilaCodigo);

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setPilaCodigo(pilaCodigo);
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setVtProyecto((FacesUtils.checkLong(txtProyCodigo_VtProyecto) != null)
                ? businessDelegatorView.getVtProyecto(FacesUtils.checkLong(
                        txtProyCodigo_VtProyecto)) : null);
            businessDelegatorView.saveVtPilaProducto(entity);
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
                Long pilaCodigo = new Long(selectedVtPilaProducto.getPilaCodigo());
                entity = businessDelegatorView.getVtPilaProducto(pilaCodigo);
            }

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setVtProyecto((FacesUtils.checkLong(txtProyCodigo_VtProyecto) != null)
                ? businessDelegatorView.getVtProyecto(FacesUtils.checkLong(
                        txtProyCodigo_VtProyecto)) : null);
            businessDelegatorView.updateVtPilaProducto(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedVtPilaProducto = (VtPilaProductoDTO) (evt.getComponent()
                                                             .getAttributes()
                                                             .get("selectedVtPilaProducto"));

            Long pilaCodigo = new Long(selectedVtPilaProducto.getPilaCodigo());
            entity = businessDelegatorView.getVtPilaProducto(pilaCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long pilaCodigo = FacesUtils.checkLong(txtPilaCodigo);
            entity = businessDelegatorView.getVtPilaProducto(pilaCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteVtPilaProducto(entity);
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
            selectedVtPilaProducto = (VtPilaProductoDTO) (evt.getComponent()
                                                             .getAttributes()
                                                             .get("selectedVtPilaProducto"));

            Long pilaCodigo = new Long(selectedVtPilaProducto.getPilaCodigo());
            entity = businessDelegatorView.getVtPilaProducto(pilaCodigo);
            businessDelegatorView.deleteVtPilaProducto(entity);
            data.remove(selectedVtPilaProducto);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String activo, String descripcion,
        Date fechaCreacion, Date fechaModificacion, String nombre,
        Long pilaCodigo, Long usuCreador, Long usuModificador,
        Long proyCodigo_VtProyecto) throws Exception {
        try {
            entity.setActivo(FacesUtils.checkString(activo));
            entity.setDescripcion(FacesUtils.checkString(descripcion));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(fechaModificacion));
            entity.setNombre(FacesUtils.checkString(nombre));
            entity.setUsuCreador(FacesUtils.checkLong(usuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(usuModificador));
            businessDelegatorView.updateVtPilaProducto(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("VtPilaProductoView").requestRender();
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

    public InputText getTxtProyCodigo_VtProyecto() {
        return txtProyCodigo_VtProyecto;
    }

    public void setTxtProyCodigo_VtProyecto(InputText txtProyCodigo_VtProyecto) {
        this.txtProyCodigo_VtProyecto = txtProyCodigo_VtProyecto;
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

    public InputText getTxtPilaCodigo() {
        return txtPilaCodigo;
    }

    public void setTxtPilaCodigo(InputText txtPilaCodigo) {
        this.txtPilaCodigo = txtPilaCodigo;
    }

    public List<VtPilaProductoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataVtPilaProducto();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<VtPilaProductoDTO> vtPilaProductoDTO) {
        this.data = vtPilaProductoDTO;
    }

    public VtPilaProductoDTO getSelectedVtPilaProducto() {
        return selectedVtPilaProducto;
    }

    public void setSelectedVtPilaProducto(VtPilaProductoDTO vtPilaProducto) {
        this.selectedVtPilaProducto = vtPilaProducto;
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
