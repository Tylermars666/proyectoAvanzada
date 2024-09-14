package co.edu.uniquindio.proyecto.services.implement;

import co.edu.uniquindio.proyecto.dto.cuponDTO.CrearCuponDTO;
import co.edu.uniquindio.proyecto.dto.cuponDTO.EditarCuponDTO;
import co.edu.uniquindio.proyecto.dto.cuponDTO.InformacionCuponDTO;
import co.edu.uniquindio.proyecto.dto.cuponDTO.ResumenCuponDTO;
import co.edu.uniquindio.proyecto.model.docs.Cuenta;
import co.edu.uniquindio.proyecto.model.docs.Cupon;
import co.edu.uniquindio.proyecto.model.enums.EstadoCupon;
import co.edu.uniquindio.proyecto.repositories.CuponRepo;
import co.edu.uniquindio.proyecto.services.interfaces.CuponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CuponServiceImpl implements CuponService {

    private final CuponRepo cuponRepo;


    @Override
    public String crearCupon(CrearCuponDTO cuponDTO) throws Exception {
        if (cuponRepo.findByCodigo(cuponDTO.codigo()).isPresent()) {
            throw new Exception("Ya existe un cupón con el código " + cuponDTO.codigo());
        }

        Cupon nuevoCupon = new Cupon();
        nuevoCupon.setCodigo(cuponDTO.codigo());
        nuevoCupon.setDescuento(cuponDTO.descuento());
        nuevoCupon.setFechaVencimiento(cuponDTO.fechaVencimiento());
        nuevoCupon.setLimiteUso(cuponDTO.limiteUso());
        nuevoCupon.setTipoCupon(cuponDTO.tipoCupon());
        nuevoCupon.setEstado(EstadoCupon.ACTIVO);

        Cupon cuponCreado = cuponRepo.save(nuevoCupon);
        return cuponCreado.getId();
    }

    @Override
    public String editarCupon(EditarCuponDTO cuponDTO) throws Exception {
        Cupon cupon = getCupon(cuponDTO.id());

        cupon.setCodigo(cuponDTO.codigo());
        cupon.setDescuento(cuponDTO.descuento());
        cupon.setFechaVencimiento(cuponDTO.fecha_Vencimiento());
        cupon.setLimiteUso(cuponDTO.limiteUso());
        cupon.setTipoCupon(cuponDTO.tipoCupon());

        cuponRepo.save(cupon);
        return cupon.getId();
    }

    @Override
    public String eliminarCupon(String id) throws Exception {
        Cupon cupon = getCupon(id);
        cupon.setEstado(EstadoCupon.INACTIVO);
        cuponRepo.save(cupon);
        return id;
    }

    @Override
    @Transactional(readOnly = true)
    public InformacionCuponDTO obtenerInformacionCupon(String id) throws Exception {
        Cupon cupon = getCupon(id);
        return mapToInformacionCuponDTO(cupon);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResumenCuponDTO> listarCupones() {
        List<Cupon> cupones = cuponRepo.findAll();
        return cupones.stream()
                .map(this::mapToResumenCuponDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResumenCuponDTO> buscarCuponesPorCodigo(String codigo) {
        List<Cupon> cupones = cuponRepo.findByCodigoContainingIgnoreCase(codigo);
        return cupones.stream()
                .map(this::mapToResumenCuponDTO)
                .toList();
    }

    //public boolean estaVigente(){

    //}

   /* @Override
    @Transactional(readOnly = true)
    public List<ResumenCuponDTO> buscarCuponesUtilizadosPorUsuario(String idUsuario) {
        List<Cupon> cupones = cuponRepo.findByUsuariosUsadosContaining(idUsuario);
        return cupones.stream()
                .map(this::mapToResumenCuponDTO)
                .toList();
    }*/

    //ANALIZAR FUNCIONALIDAD DE ESTOS MÉTODOS

    private InformacionCuponDTO mapToInformacionCuponDTO(Cupon cupon) {
        return new InformacionCuponDTO(
                cupon.getCodigo(),
                cupon.getDescuento(),
                cupon.getFechaVencimiento(),
                cupon.getLimiteUso(),
                cupon.getTipoCupon(),
                cupon.getEstado()
        );
    }

    private ResumenCuponDTO mapToResumenCuponDTO(Cupon cupon) {
        return new ResumenCuponDTO(
                cupon.getCodigo(),
                cupon.getDescuento(),
                cupon.getFechaVencimiento(),
                cupon.getEstado()
        );
    }


    //---------------MÉTODOS AUXILIARES-----------------

    private Cupon getCupon(String id) throws Exception {

        Optional<Cupon> optionalCupon = cuponRepo.findById(id);                 //Buscamos el cupon  que se quiere obtener

        if(optionalCupon.isEmpty()){
            throw new Exception("No se encontró el cupon con el id "+id);        //Si no se encontró el cupon, lanzamos una excepción
        }

        return optionalCupon.get();

    }

}
