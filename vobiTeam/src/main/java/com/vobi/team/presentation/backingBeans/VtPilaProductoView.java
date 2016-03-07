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
import javax.faces.model.SelectItem;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vobi.team.modelo.VtEmpresa;
import com.vobi.team.modelo.VtPilaProducto;
import com.vobi.team.modelo.VtProyecto;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;

@ManagedBean 
@ViewScoped
public class VtPilaProductoView implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(VtEmpresaView.class);

	private List<SelectItem> lasEmpresasItems;

	private SelectOneMenu somEmpresas;
	private SelectOneMenu somActivo;
	private SelectOneMenu somProyectos;

	private CommandButton btnCrear;
	private CommandButton btnModificar;
	private CommandButton btnLimpiar;

	String stringActivo;

	private InputText txtNombre;
	private InputTextarea txtDescripcion;
	private List<SelectItem> esActivoItems;
	private List<SelectItem> losProyectosItems;

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
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

	public List<SelectItem> getEsActivoItems() {
		if (esActivoItems == null) {
			esActivoItems = new ArrayList<SelectItem>();
			esActivoItems.add(new SelectItem("Si"));
			esActivoItems.add(new SelectItem("No"));

		}
		return esActivoItems;
	}

	public void setEsActivoItems(List<SelectItem> esActivoItems) {
		this.esActivoItems = esActivoItems;
	}

	public List<SelectItem> getLosProyectosItems() {
		try {
			if (losProyectosItems == null) {
				List<VtProyecto> listaProyectos = businessDelegatorView.getVtProyecto();
				losProyectosItems = new ArrayList<SelectItem>();

				for (VtProyecto vtProyecto : listaProyectos) {
					losProyectosItems.add(new SelectItem(vtProyecto.getProyCodigo(), vtProyecto.getNombre()));
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return losProyectosItems;
	}

	public void setLosProyectosItems(List<SelectItem> losProyectosItems) {
		this.losProyectosItems = losProyectosItems;
	}

	public SelectOneMenu getSomActivo() {
		return somActivo;
	}

	public void setSomActivo(SelectOneMenu somActivo) {
		this.somActivo = somActivo;
	}

	public SelectOneMenu getSomProyectos() {
		return somProyectos;
	}

	public void setSomProyectos(SelectOneMenu somProyectos) {
		this.somProyectos = somProyectos;
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

	public String getStringActivo() {
		return stringActivo;
	}

	public void setStringActivo(String stringActivo) {
		this.stringActivo = stringActivo;
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

	public String crearPilaProducto() throws Exception {
		log.info("Creando la pila de producto");

		VtPilaProducto vtPilaProducto = new VtPilaProducto();
		vtPilaProducto.setNombre(txtNombre.getValue().toString().trim());
		vtPilaProducto.setDescripcion(txtDescripcion.getValue().toString().trim());
		Date fecha = new Date();
		vtPilaProducto.setFechaCreacion(fecha);

		String activo = somActivo.getValue().toString().trim();
		if (activo.equals("Si")) {
			vtPilaProducto.setActivo("S");
		} else {
			vtPilaProducto.setActivo("N");
		}

		String proyectos = somProyectos.getValue().toString().trim();
		Long proyecto = Long.parseLong(proyectos);
		VtProyecto vtProyecto = businessDelegatorView.getVtProyecto(proyecto);
		vtPilaProducto.setUsuCreador(1L);
		vtPilaProducto.setVtProyecto(vtProyecto);

		try {
			businessDelegatorView.saveVtPilaProducto(vtPilaProducto);
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage("La pila de producto se creo con exito"));
			limpiar();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(e.getMessage()));
		}

		return "";
	}

	public String limpiar() {
		log.info("Limpiando campos de texto");
		txtNombre.resetValue();
		txtDescripcion.resetValue();
		somActivo.setValue("-1");
		somProyectos.setValue("-1");

		btnCrear.setDisabled(true);
		return "";
	}

}
