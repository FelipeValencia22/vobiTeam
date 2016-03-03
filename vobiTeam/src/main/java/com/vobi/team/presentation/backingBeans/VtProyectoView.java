package com.vobi.team.presentation.backingBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vobi.team.modelo.VtEmpresa;
import com.vobi.team.modelo.VtProyecto;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;

import com.vobi.team.utilities.FacesUtils;

import com.vobi.team.exceptions.ZMessManager;

import com.vobi.team.modelo.dto.VtProyectoDTO;

@ManagedBean
@ViewScoped
public class VtProyectoView implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(VtProyectoView.class);

	private InputText txtNombre;
	private InputText txtActivo;
	private InputText txtPublico;
	private InputText txtEmpresa;
	private InputTextarea txtDescripcion;
	private List<SelectItem> esPublicoItems;
	private List<SelectItem> esActivoItems;
	private List<SelectItem> lasEmpresasItems;

	private SelectOneMenu somPublico;
	private SelectOneMenu somActivo;
	private SelectOneMenu somEmpresas;

	private CommandButton btnCrear;
	private CommandButton btnModificar;
	private CommandButton btnLimpiar;

	String stringActivo;

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public VtProyectoView() {
		super();
	}

	public InputText getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
	}

	public InputTextarea getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(InputTextarea txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}

	public List<SelectItem> getEsPublicoItems() {
		if(esPublicoItems==null){
			esPublicoItems=new ArrayList<SelectItem>();
			esPublicoItems.add(new SelectItem("Público"));
			esPublicoItems.add(new SelectItem("Privado"));

		}
		return esPublicoItems;
	}

	public void setEsPublicoItems(List<SelectItem> esPublicoItems) {
		this.esPublicoItems = esPublicoItems;
	}

	public List<SelectItem> getEsActivoItems() {
		if(esActivoItems==null){
			esActivoItems=new ArrayList<SelectItem>();
			esActivoItems.add(new SelectItem("Si"));
			esActivoItems.add(new SelectItem("No"));

		}
		return esActivoItems;
	}

	public void setEsActivoItems(List<SelectItem> esActivoItems) {
		this.esActivoItems = esActivoItems;
	}

	public CommandButton getBtnCrear() {
		return btnCrear;
	}

	public void setBtnCrear(CommandButton btnCrear) {
		this.btnCrear = btnCrear;
	}

	public CommandButton getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(CommandButton btnModificar) {
		this.btnModificar = btnModificar;
	}

	public CommandButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public void setBtnLimpiar(CommandButton btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}

	public SelectOneMenu getSomPublico() {
		return somPublico;
	}

	public void setSomPublico(SelectOneMenu somPublico) {
		this.somPublico = somPublico;
	}

	public SelectOneMenu getSomActivo() {
		return somActivo;
	}

	public void setSomActivo(SelectOneMenu somActivo) {
		this.somActivo = somActivo;
	}

	public List<SelectItem> getLasEmpresasItems() {
		try {
			if(lasEmpresasItems==null){
				List<VtEmpresa> listaEmpresas=businessDelegatorView.getVtEmpresa();
				lasEmpresasItems=new ArrayList<SelectItem>();
				for (VtEmpresa vtEmpresa: listaEmpresas) {
					lasEmpresasItems.add(new SelectItem(vtEmpresa.getEmprCodigo(), vtEmpresa.getNombre()));
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return lasEmpresasItems;
	}

	public void setLasEmpresasItems(List<SelectItem> lasEmpresasItems) {
		this.lasEmpresasItems = lasEmpresasItems;
	}

	public SelectOneMenu getSomEmpresas() {
		return somEmpresas;
	}

	public void setSomEmpresas(SelectOneMenu somEmpresas) {
		this.somEmpresas = somEmpresas;
	}


	public String getStringActivo() {
		return stringActivo;
	}

	public void setStringActivo(String stringActivo) {
		this.stringActivo = stringActivo;
	}

	public String crearProyecto() throws Exception{
		log.info("Creando proyecto");

		VtProyecto vtProyecto= new VtProyecto();

		vtProyecto.setDescripcion(txtDescripcion.getValue().toString().trim());
		Date fechaCreacion = new Date();
		vtProyecto.setFechaCreacion(fechaCreacion);
		vtProyecto.setNombre(txtNombre.getValue().toString().trim());

		String publico=somPublico.getValue().toString().trim();
		if(publico.equals("Público")){
			vtProyecto.setPublico("S");
		}else{
			vtProyecto.setPublico("N");
		}

		String activo=somActivo.getValue().toString().trim();
		if(activo.equals("Si")){
			vtProyecto.setActivo("S");
		}else{
			vtProyecto.setActivo("N");
		}

		vtProyecto.setUsuCreador(1L);

		String empresaS=somEmpresas.getValue().toString().trim();
		if(empresaS.isEmpty() || empresaS.equals("-1")){
			VtEmpresa vtEmpresaNA=businessDelegatorView.getVtEmpresa(142020L);
			vtProyecto.setVtEmpresa(vtEmpresaNA);
		}else{
			Long empresa=Long.parseLong(empresaS);
			VtEmpresa vtEmpresa=businessDelegatorView.getVtEmpresa(empresa);
			vtProyecto.setVtEmpresa(vtEmpresa);
		}

		try {
			businessDelegatorView.saveVtProyecto(vtProyecto);
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage("El proyecto se creo con exito"));
			limpiar();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(e.getMessage()));
		}

		return "";
	}

	public String limpiar(){
		log.info("Limpiando campos de texto");
		txtNombre.resetValue();
		txtDescripcion.resetValue();
		somPublico.setValue("-1");
		somActivo.setValue("-1");
		somEmpresas.setValue("-1");
		
		btnCrear.setDisabled(true);
		return "";
	}
	
	public InputText getTxtActivo() {
		return txtActivo;
	}

	public void setTxtActivo(InputText txtActivo) {
		this.txtActivo = txtActivo;
	}

	public InputText getTxtPublico() {
		return txtPublico;
	}

	public void setTxtPublico(InputText txtPublico) {
		this.txtPublico = txtPublico;
	}

	public InputText getTxtEmpresa() {
		return txtEmpresa;
	}

	public void setTxtEmpresa(InputText txtEmpresa) {
		this.txtEmpresa = txtEmpresa;
	}

	/// tabla con registros editables

	private VtProyecto entity;
	
	private CommandButton btnSave;
	
	private List<VtProyectoDTO> data;
	
	private VtProyectoDTO selectedVtProyecto;
	
	private boolean showDialog;
	
	private InputText txtProyCodigo;
	
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
    
    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }
    
    public InputText getTxtProyCodigo() {
        return txtProyCodigo;
    }

    public void setTxtProyCodigo(InputText txtProyCodigo) {
        this.txtProyCodigo = txtProyCodigo;
    }
    
    public CommandButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(CommandButton btnSave) {
		this.btnSave = btnSave;
	}

	public String action_edit(ActionEvent evt) {
        selectedVtProyecto = (VtProyectoDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedVtProyecto"));
        txtActivo.setValue(selectedVtProyecto.getActivo());
        txtActivo.setDisabled(false);
        txtDescripcion.setValue(selectedVtProyecto.getDescripcion());
        txtDescripcion.setDisabled(false);
        txtNombre.setValue(selectedVtProyecto.getNombre());
        txtNombre.setDisabled(false);
        txtPublico.setValue(selectedVtProyecto.getPublico());
        txtPublico.setDisabled(false);
        txtEmpresa.setValue(selectedVtProyecto.getProyCodigo());
        txtEmpresa.setDisabled(true);
        //btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
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
            txtProyCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtActivo.setValue(entity.getActivo());
            txtActivo.setDisabled(false);
            txtDescripcion.setValue(entity.getDescripcion());
            txtDescripcion.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtPublico.setValue(entity.getPublico());
            txtPublico.setDisabled(false);
            txtEmpresa.setValue(entity.getVtEmpresa().getEmprCodigo());
            txtEmpresa.setDisabled(false);
            txtProyCodigo.setValue(entity.getProyCodigo());
            txtProyCodigo.setDisabled(true);
            btnSave.setDisabled(false);
        }
    }
    
    public String action_save() {
        try {
            if ((selectedVtProyecto == null) && (entity == null)) {

            } else {
                action_modify();
            }

            data = null;
        } catch (Exception e) {
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
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setPublico(FacesUtils.checkString(txtPublico));
            //TODO: entity.setVtEmpresa((FacesUtils.checkLong(txtEmpresa));
            businessDelegatorView.updateVtProyecto(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

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

       

        if (txtProyCodigo != null) {
            txtProyCodigo.setValue(null);
            txtProyCodigo.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }


        return "";
    }
	
    public String action_closeDialog() {
        setShowDialog(false);
        action_clear();

        return "";
    }
	
}
