package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.BuckleArrestorHist;
import io.github.jhipster.application.domain.BuckleArrestor;
import io.github.jhipster.application.domain.PipelineSection;
import io.github.jhipster.application.domain.ListBucklarrType;
import io.github.jhipster.application.domain.ListInternalCoating;
import io.github.jhipster.application.domain.ListExternalCoating;
import io.github.jhipster.application.domain.ListNominalWallThickness;
import io.github.jhipster.application.domain.Pipejoint;
import io.github.jhipster.application.domain.ListBucklarrManufacturer;
import io.github.jhipster.application.domain.ListBucklarrSpecification;
import io.github.jhipster.application.domain.ListLongSeamWeldType;
import io.github.jhipster.application.domain.ListFabricationType;
import io.github.jhipster.application.domain.ListMaterial;
import io.github.jhipster.application.domain.ListMillLocation;
import io.github.jhipster.application.domain.ListSteelGrade;
import io.github.jhipster.application.repository.BuckleArrestorHistRepository;
import io.github.jhipster.application.service.BuckleArrestorHistService;
import io.github.jhipster.application.service.dto.BuckleArrestorHistDTO;
import io.github.jhipster.application.service.mapper.BuckleArrestorHistMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.BuckleArrestorHistCriteria;
import io.github.jhipster.application.service.BuckleArrestorHistQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link BuckleArrestorHistResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class BuckleArrestorHistResourceIT {

    private static final LocalDate DEFAULT_DATE_FROM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_FROM = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_TO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_TO = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_ID_WRK = 1L;
    private static final Long UPDATED_ID_WRK = 2L;

    private static final String DEFAULT_SERIAL_NUM = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL_NUM = "BBBBBBBBBB";

    private static final Integer DEFAULT_DIAMETER_OUTER_STEEL = 1;
    private static final Integer UPDATED_DIAMETER_OUTER_STEEL = 2;

    private static final BigDecimal DEFAULT_INTERNAL_COAT_THICKNESS = new BigDecimal(1);
    private static final BigDecimal UPDATED_INTERNAL_COAT_THICKNESS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_EXTERNAL_COAT_THICKNESS = new BigDecimal(1);
    private static final BigDecimal UPDATED_EXTERNAL_COAT_THICKNESS = new BigDecimal(2);

    private static final Integer DEFAULT_IS_CONCR_COATING = 1;
    private static final Integer UPDATED_IS_CONCR_COATING = 2;

    private static final BigDecimal DEFAULT_CONCR_COAT_THICKNESS = new BigDecimal(1);
    private static final BigDecimal UPDATED_CONCR_COAT_THICKNESS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CONCR_COAT_DENSITY = new BigDecimal(1);
    private static final BigDecimal UPDATED_CONCR_COAT_DENSITY = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MEAS_COL_WALL_THICKNESS = new BigDecimal(1);
    private static final BigDecimal UPDATED_MEAS_COL_WALL_THICKNESS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MEAS_PIPE_WALL_THICKNESS = new BigDecimal(1);
    private static final BigDecimal UPDATED_MEAS_PIPE_WALL_THICKNESS = new BigDecimal(2);

    private static final Integer DEFAULT_COL_LENGTH = 1;
    private static final Integer UPDATED_COL_LENGTH = 2;

    private static final Integer DEFAULT_PIPE_LENGTH = 1;
    private static final Integer UPDATED_PIPE_LENGTH = 2;

    private static final BigDecimal DEFAULT_WEIGHT = new BigDecimal(1);
    private static final BigDecimal UPDATED_WEIGHT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SMTS = new BigDecimal(1);
    private static final BigDecimal UPDATED_SMTS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SMYS = new BigDecimal(1);
    private static final BigDecimal UPDATED_SMYS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SDBM = new BigDecimal(1);
    private static final BigDecimal UPDATED_SDBM = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SDAF = new BigDecimal(1);
    private static final BigDecimal UPDATED_SDAF = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SDCS = new BigDecimal(1);
    private static final BigDecimal UPDATED_SDCS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_ALLOW_TENS_STRAIN = new BigDecimal(1);
    private static final BigDecimal UPDATED_ALLOW_TENS_STRAIN = new BigDecimal(2);

    private static final Integer DEFAULT_CORROSION_ALLOWANCE = 1;
    private static final Integer UPDATED_CORROSION_ALLOWANCE = 2;

    private static final Integer DEFAULT_FABRICATION_ALLOWANCE = 1;
    private static final Integer UPDATED_FABRICATION_ALLOWANCE = 2;

    private static final Integer DEFAULT_IS_BURIAL = 1;
    private static final Integer UPDATED_IS_BURIAL = 2;

    private static final Integer DEFAULT_BURIAL_DEPTH = 1;
    private static final Integer UPDATED_BURIAL_DEPTH = 2;

    private static final Integer DEFAULT_FACT_BURIAL_DEPTH = 1;
    private static final Integer UPDATED_FACT_BURIAL_DEPTH = 2;

    private static final LocalDate DEFAULT_DATE_MANUFACTURED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_MANUFACTURED = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_MILL_TEST_PRESSURE = new BigDecimal(1);
    private static final BigDecimal UPDATED_MILL_TEST_PRESSURE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DESIGN_PRESSURE = new BigDecimal(1);
    private static final BigDecimal UPDATED_DESIGN_PRESSURE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_INCIDENTAL_PRESSURE = new BigDecimal(1);
    private static final BigDecimal UPDATED_INCIDENTAL_PRESSURE = new BigDecimal(2);

    private static final LocalDate DEFAULT_DATE_INSTALLED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_INSTALLED = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_SEAM_ORIENT = new BigDecimal(1);
    private static final BigDecimal UPDATED_SEAM_ORIENT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SEAM_ANGLE = new BigDecimal(1);
    private static final BigDecimal UPDATED_SEAM_ANGLE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AZIMUTH = new BigDecimal(1);
    private static final BigDecimal UPDATED_AZIMUTH = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SEAB_INSTALL_TEMP = new BigDecimal(1);
    private static final BigDecimal UPDATED_SEAB_INSTALL_TEMP = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VERTICAL_ANGLE = new BigDecimal(1);
    private static final BigDecimal UPDATED_VERTICAL_ANGLE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_HEAT_TREAT_DURAT = new BigDecimal(1);
    private static final BigDecimal UPDATED_HEAT_TREAT_DURAT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MAX_DESIGN_TEMP = new BigDecimal(1);
    private static final BigDecimal UPDATED_MAX_DESIGN_TEMP = new BigDecimal(2);

    private static final String DEFAULT_HEAT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_HEAT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_COORD = "AAAAAAAAAA";
    private static final String UPDATED_COORD = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_DESIGN_COORD = new BigDecimal(1);
    private static final BigDecimal UPDATED_DESIGN_COORD = new BigDecimal(2);

    private static final BigDecimal DEFAULT_KP_START = new BigDecimal(1);
    private static final BigDecimal UPDATED_KP_START = new BigDecimal(2);

    private static final BigDecimal DEFAULT_KP_END = new BigDecimal(1);
    private static final BigDecimal UPDATED_KP_END = new BigDecimal(2);

    private static final Integer DEFAULT_IS_CURRENT_FLAG = 1;
    private static final Integer UPDATED_IS_CURRENT_FLAG = 2;

    private static final Long DEFAULT_ID_STATUS = 1L;
    private static final Long UPDATED_ID_STATUS = 2L;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE_CREATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_CREATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_EDIT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_EDIT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final String DEFAULT_EDITOR = "AAAAAAAAAA";
    private static final String UPDATED_EDITOR = "BBBBBBBBBB";

    @Autowired
    private BuckleArrestorHistRepository buckleArrestorHistRepository;

    @Autowired
    private BuckleArrestorHistMapper buckleArrestorHistMapper;

    @Autowired
    private BuckleArrestorHistService buckleArrestorHistService;

    @Autowired
    private BuckleArrestorHistQueryService buckleArrestorHistQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restBuckleArrestorHistMockMvc;

    private BuckleArrestorHist buckleArrestorHist;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BuckleArrestorHistResource buckleArrestorHistResource = new BuckleArrestorHistResource(buckleArrestorHistService, buckleArrestorHistQueryService);
        this.restBuckleArrestorHistMockMvc = MockMvcBuilders.standaloneSetup(buckleArrestorHistResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BuckleArrestorHist createEntity(EntityManager em) {
        BuckleArrestorHist buckleArrestorHist = new BuckleArrestorHist()
            .dateFrom(DEFAULT_DATE_FROM)
            .dateTo(DEFAULT_DATE_TO)
            .idWrk(DEFAULT_ID_WRK)
            .serialNum(DEFAULT_SERIAL_NUM)
            .diameterOuterSteel(DEFAULT_DIAMETER_OUTER_STEEL)
            .internalCoatThickness(DEFAULT_INTERNAL_COAT_THICKNESS)
            .externalCoatThickness(DEFAULT_EXTERNAL_COAT_THICKNESS)
            .isConcrCoating(DEFAULT_IS_CONCR_COATING)
            .concrCoatThickness(DEFAULT_CONCR_COAT_THICKNESS)
            .concrCoatDensity(DEFAULT_CONCR_COAT_DENSITY)
            .measColWallThickness(DEFAULT_MEAS_COL_WALL_THICKNESS)
            .measPipeWallThickness(DEFAULT_MEAS_PIPE_WALL_THICKNESS)
            .colLength(DEFAULT_COL_LENGTH)
            .pipeLength(DEFAULT_PIPE_LENGTH)
            .weight(DEFAULT_WEIGHT)
            .smts(DEFAULT_SMTS)
            .smys(DEFAULT_SMYS)
            .sdbm(DEFAULT_SDBM)
            .sdaf(DEFAULT_SDAF)
            .sdcs(DEFAULT_SDCS)
            .allowTensStrain(DEFAULT_ALLOW_TENS_STRAIN)
            .corrosionAllowance(DEFAULT_CORROSION_ALLOWANCE)
            .fabricationAllowance(DEFAULT_FABRICATION_ALLOWANCE)
            .isBurial(DEFAULT_IS_BURIAL)
            .burialDepth(DEFAULT_BURIAL_DEPTH)
            .factBurialDepth(DEFAULT_FACT_BURIAL_DEPTH)
            .dateManufactured(DEFAULT_DATE_MANUFACTURED)
            .millTestPressure(DEFAULT_MILL_TEST_PRESSURE)
            .designPressure(DEFAULT_DESIGN_PRESSURE)
            .incidentalPressure(DEFAULT_INCIDENTAL_PRESSURE)
            .dateInstalled(DEFAULT_DATE_INSTALLED)
            .seamOrient(DEFAULT_SEAM_ORIENT)
            .seamAngle(DEFAULT_SEAM_ANGLE)
            .azimuth(DEFAULT_AZIMUTH)
            .seabInstallTemp(DEFAULT_SEAB_INSTALL_TEMP)
            .verticalAngle(DEFAULT_VERTICAL_ANGLE)
            .heatTreatDurat(DEFAULT_HEAT_TREAT_DURAT)
            .maxDesignTemp(DEFAULT_MAX_DESIGN_TEMP)
            .heatNumber(DEFAULT_HEAT_NUMBER)
            .coord(DEFAULT_COORD)
            .designCoord(DEFAULT_DESIGN_COORD)
            .kpStart(DEFAULT_KP_START)
            .kpEnd(DEFAULT_KP_END)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .idStatus(DEFAULT_ID_STATUS)
            .description(DEFAULT_DESCRIPTION)
            .comment(DEFAULT_COMMENT)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        // Add required entity
        BuckleArrestor buckleArrestor;
        if (TestUtil.findAll(em, BuckleArrestor.class).isEmpty()) {
            buckleArrestor = BuckleArrestorResourceIT.createEntity(em);
            em.persist(buckleArrestor);
            em.flush();
        } else {
            buckleArrestor = TestUtil.findAll(em, BuckleArrestor.class).get(0);
        }
        buckleArrestorHist.setId(buckleArrestor);
        // Add required entity
        PipelineSection pipelineSection;
        if (TestUtil.findAll(em, PipelineSection.class).isEmpty()) {
            pipelineSection = PipelineSectionResourceIT.createEntity(em);
            em.persist(pipelineSection);
            em.flush();
        } else {
            pipelineSection = TestUtil.findAll(em, PipelineSection.class).get(0);
        }
        buckleArrestorHist.setIdPipelineSection(pipelineSection);
        // Add required entity
        ListBucklarrType listBucklarrType;
        if (TestUtil.findAll(em, ListBucklarrType.class).isEmpty()) {
            listBucklarrType = ListBucklarrTypeResourceIT.createEntity(em);
            em.persist(listBucklarrType);
            em.flush();
        } else {
            listBucklarrType = TestUtil.findAll(em, ListBucklarrType.class).get(0);
        }
        buckleArrestorHist.setIdType(listBucklarrType);
        // Add required entity
        ListInternalCoating listInternalCoating;
        if (TestUtil.findAll(em, ListInternalCoating.class).isEmpty()) {
            listInternalCoating = ListInternalCoatingResourceIT.createEntity(em);
            em.persist(listInternalCoating);
            em.flush();
        } else {
            listInternalCoating = TestUtil.findAll(em, ListInternalCoating.class).get(0);
        }
        buckleArrestorHist.setIdInternalCoatType(listInternalCoating);
        // Add required entity
        ListExternalCoating listExternalCoating;
        if (TestUtil.findAll(em, ListExternalCoating.class).isEmpty()) {
            listExternalCoating = ListExternalCoatingResourceIT.createEntity(em);
            em.persist(listExternalCoating);
            em.flush();
        } else {
            listExternalCoating = TestUtil.findAll(em, ListExternalCoating.class).get(0);
        }
        buckleArrestorHist.setIdExternalCoatType(listExternalCoating);
        return buckleArrestorHist;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BuckleArrestorHist createUpdatedEntity(EntityManager em) {
        BuckleArrestorHist buckleArrestorHist = new BuckleArrestorHist()
            .dateFrom(UPDATED_DATE_FROM)
            .dateTo(UPDATED_DATE_TO)
            .idWrk(UPDATED_ID_WRK)
            .serialNum(UPDATED_SERIAL_NUM)
            .diameterOuterSteel(UPDATED_DIAMETER_OUTER_STEEL)
            .internalCoatThickness(UPDATED_INTERNAL_COAT_THICKNESS)
            .externalCoatThickness(UPDATED_EXTERNAL_COAT_THICKNESS)
            .isConcrCoating(UPDATED_IS_CONCR_COATING)
            .concrCoatThickness(UPDATED_CONCR_COAT_THICKNESS)
            .concrCoatDensity(UPDATED_CONCR_COAT_DENSITY)
            .measColWallThickness(UPDATED_MEAS_COL_WALL_THICKNESS)
            .measPipeWallThickness(UPDATED_MEAS_PIPE_WALL_THICKNESS)
            .colLength(UPDATED_COL_LENGTH)
            .pipeLength(UPDATED_PIPE_LENGTH)
            .weight(UPDATED_WEIGHT)
            .smts(UPDATED_SMTS)
            .smys(UPDATED_SMYS)
            .sdbm(UPDATED_SDBM)
            .sdaf(UPDATED_SDAF)
            .sdcs(UPDATED_SDCS)
            .allowTensStrain(UPDATED_ALLOW_TENS_STRAIN)
            .corrosionAllowance(UPDATED_CORROSION_ALLOWANCE)
            .fabricationAllowance(UPDATED_FABRICATION_ALLOWANCE)
            .isBurial(UPDATED_IS_BURIAL)
            .burialDepth(UPDATED_BURIAL_DEPTH)
            .factBurialDepth(UPDATED_FACT_BURIAL_DEPTH)
            .dateManufactured(UPDATED_DATE_MANUFACTURED)
            .millTestPressure(UPDATED_MILL_TEST_PRESSURE)
            .designPressure(UPDATED_DESIGN_PRESSURE)
            .incidentalPressure(UPDATED_INCIDENTAL_PRESSURE)
            .dateInstalled(UPDATED_DATE_INSTALLED)
            .seamOrient(UPDATED_SEAM_ORIENT)
            .seamAngle(UPDATED_SEAM_ANGLE)
            .azimuth(UPDATED_AZIMUTH)
            .seabInstallTemp(UPDATED_SEAB_INSTALL_TEMP)
            .verticalAngle(UPDATED_VERTICAL_ANGLE)
            .heatTreatDurat(UPDATED_HEAT_TREAT_DURAT)
            .maxDesignTemp(UPDATED_MAX_DESIGN_TEMP)
            .heatNumber(UPDATED_HEAT_NUMBER)
            .coord(UPDATED_COORD)
            .designCoord(UPDATED_DESIGN_COORD)
            .kpStart(UPDATED_KP_START)
            .kpEnd(UPDATED_KP_END)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .idStatus(UPDATED_ID_STATUS)
            .description(UPDATED_DESCRIPTION)
            .comment(UPDATED_COMMENT)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        // Add required entity
        BuckleArrestor buckleArrestor;
        if (TestUtil.findAll(em, BuckleArrestor.class).isEmpty()) {
            buckleArrestor = BuckleArrestorResourceIT.createUpdatedEntity(em);
            em.persist(buckleArrestor);
            em.flush();
        } else {
            buckleArrestor = TestUtil.findAll(em, BuckleArrestor.class).get(0);
        }
        buckleArrestorHist.setId(buckleArrestor);
        // Add required entity
        PipelineSection pipelineSection;
        if (TestUtil.findAll(em, PipelineSection.class).isEmpty()) {
            pipelineSection = PipelineSectionResourceIT.createUpdatedEntity(em);
            em.persist(pipelineSection);
            em.flush();
        } else {
            pipelineSection = TestUtil.findAll(em, PipelineSection.class).get(0);
        }
        buckleArrestorHist.setIdPipelineSection(pipelineSection);
        // Add required entity
        ListBucklarrType listBucklarrType;
        if (TestUtil.findAll(em, ListBucklarrType.class).isEmpty()) {
            listBucklarrType = ListBucklarrTypeResourceIT.createUpdatedEntity(em);
            em.persist(listBucklarrType);
            em.flush();
        } else {
            listBucklarrType = TestUtil.findAll(em, ListBucklarrType.class).get(0);
        }
        buckleArrestorHist.setIdType(listBucklarrType);
        // Add required entity
        ListInternalCoating listInternalCoating;
        if (TestUtil.findAll(em, ListInternalCoating.class).isEmpty()) {
            listInternalCoating = ListInternalCoatingResourceIT.createUpdatedEntity(em);
            em.persist(listInternalCoating);
            em.flush();
        } else {
            listInternalCoating = TestUtil.findAll(em, ListInternalCoating.class).get(0);
        }
        buckleArrestorHist.setIdInternalCoatType(listInternalCoating);
        // Add required entity
        ListExternalCoating listExternalCoating;
        if (TestUtil.findAll(em, ListExternalCoating.class).isEmpty()) {
            listExternalCoating = ListExternalCoatingResourceIT.createUpdatedEntity(em);
            em.persist(listExternalCoating);
            em.flush();
        } else {
            listExternalCoating = TestUtil.findAll(em, ListExternalCoating.class).get(0);
        }
        buckleArrestorHist.setIdExternalCoatType(listExternalCoating);
        return buckleArrestorHist;
    }

    @BeforeEach
    public void initTest() {
        buckleArrestorHist = createEntity(em);
    }

    @Test
    @Transactional
    public void createBuckleArrestorHist() throws Exception {
        int databaseSizeBeforeCreate = buckleArrestorHistRepository.findAll().size();

        // Create the BuckleArrestorHist
        BuckleArrestorHistDTO buckleArrestorHistDTO = buckleArrestorHistMapper.toDto(buckleArrestorHist);
        restBuckleArrestorHistMockMvc.perform(post("/api/buckle-arrestor-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buckleArrestorHistDTO)))
            .andExpect(status().isCreated());

        // Validate the BuckleArrestorHist in the database
        List<BuckleArrestorHist> buckleArrestorHistList = buckleArrestorHistRepository.findAll();
        assertThat(buckleArrestorHistList).hasSize(databaseSizeBeforeCreate + 1);
        BuckleArrestorHist testBuckleArrestorHist = buckleArrestorHistList.get(buckleArrestorHistList.size() - 1);
        assertThat(testBuckleArrestorHist.getDateFrom()).isEqualTo(DEFAULT_DATE_FROM);
        assertThat(testBuckleArrestorHist.getDateTo()).isEqualTo(DEFAULT_DATE_TO);
        assertThat(testBuckleArrestorHist.getIdWrk()).isEqualTo(DEFAULT_ID_WRK);
        assertThat(testBuckleArrestorHist.getSerialNum()).isEqualTo(DEFAULT_SERIAL_NUM);
        assertThat(testBuckleArrestorHist.getDiameterOuterSteel()).isEqualTo(DEFAULT_DIAMETER_OUTER_STEEL);
        assertThat(testBuckleArrestorHist.getInternalCoatThickness()).isEqualTo(DEFAULT_INTERNAL_COAT_THICKNESS);
        assertThat(testBuckleArrestorHist.getExternalCoatThickness()).isEqualTo(DEFAULT_EXTERNAL_COAT_THICKNESS);
        assertThat(testBuckleArrestorHist.getIsConcrCoating()).isEqualTo(DEFAULT_IS_CONCR_COATING);
        assertThat(testBuckleArrestorHist.getConcrCoatThickness()).isEqualTo(DEFAULT_CONCR_COAT_THICKNESS);
        assertThat(testBuckleArrestorHist.getConcrCoatDensity()).isEqualTo(DEFAULT_CONCR_COAT_DENSITY);
        assertThat(testBuckleArrestorHist.getMeasColWallThickness()).isEqualTo(DEFAULT_MEAS_COL_WALL_THICKNESS);
        assertThat(testBuckleArrestorHist.getMeasPipeWallThickness()).isEqualTo(DEFAULT_MEAS_PIPE_WALL_THICKNESS);
        assertThat(testBuckleArrestorHist.getColLength()).isEqualTo(DEFAULT_COL_LENGTH);
        assertThat(testBuckleArrestorHist.getPipeLength()).isEqualTo(DEFAULT_PIPE_LENGTH);
        assertThat(testBuckleArrestorHist.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testBuckleArrestorHist.getSmts()).isEqualTo(DEFAULT_SMTS);
        assertThat(testBuckleArrestorHist.getSmys()).isEqualTo(DEFAULT_SMYS);
        assertThat(testBuckleArrestorHist.getSdbm()).isEqualTo(DEFAULT_SDBM);
        assertThat(testBuckleArrestorHist.getSdaf()).isEqualTo(DEFAULT_SDAF);
        assertThat(testBuckleArrestorHist.getSdcs()).isEqualTo(DEFAULT_SDCS);
        assertThat(testBuckleArrestorHist.getAllowTensStrain()).isEqualTo(DEFAULT_ALLOW_TENS_STRAIN);
        assertThat(testBuckleArrestorHist.getCorrosionAllowance()).isEqualTo(DEFAULT_CORROSION_ALLOWANCE);
        assertThat(testBuckleArrestorHist.getFabricationAllowance()).isEqualTo(DEFAULT_FABRICATION_ALLOWANCE);
        assertThat(testBuckleArrestorHist.getIsBurial()).isEqualTo(DEFAULT_IS_BURIAL);
        assertThat(testBuckleArrestorHist.getBurialDepth()).isEqualTo(DEFAULT_BURIAL_DEPTH);
        assertThat(testBuckleArrestorHist.getFactBurialDepth()).isEqualTo(DEFAULT_FACT_BURIAL_DEPTH);
        assertThat(testBuckleArrestorHist.getDateManufactured()).isEqualTo(DEFAULT_DATE_MANUFACTURED);
        assertThat(testBuckleArrestorHist.getMillTestPressure()).isEqualTo(DEFAULT_MILL_TEST_PRESSURE);
        assertThat(testBuckleArrestorHist.getDesignPressure()).isEqualTo(DEFAULT_DESIGN_PRESSURE);
        assertThat(testBuckleArrestorHist.getIncidentalPressure()).isEqualTo(DEFAULT_INCIDENTAL_PRESSURE);
        assertThat(testBuckleArrestorHist.getDateInstalled()).isEqualTo(DEFAULT_DATE_INSTALLED);
        assertThat(testBuckleArrestorHist.getSeamOrient()).isEqualTo(DEFAULT_SEAM_ORIENT);
        assertThat(testBuckleArrestorHist.getSeamAngle()).isEqualTo(DEFAULT_SEAM_ANGLE);
        assertThat(testBuckleArrestorHist.getAzimuth()).isEqualTo(DEFAULT_AZIMUTH);
        assertThat(testBuckleArrestorHist.getSeabInstallTemp()).isEqualTo(DEFAULT_SEAB_INSTALL_TEMP);
        assertThat(testBuckleArrestorHist.getVerticalAngle()).isEqualTo(DEFAULT_VERTICAL_ANGLE);
        assertThat(testBuckleArrestorHist.getHeatTreatDurat()).isEqualTo(DEFAULT_HEAT_TREAT_DURAT);
        assertThat(testBuckleArrestorHist.getMaxDesignTemp()).isEqualTo(DEFAULT_MAX_DESIGN_TEMP);
        assertThat(testBuckleArrestorHist.getHeatNumber()).isEqualTo(DEFAULT_HEAT_NUMBER);
        assertThat(testBuckleArrestorHist.getCoord()).isEqualTo(DEFAULT_COORD);
        assertThat(testBuckleArrestorHist.getDesignCoord()).isEqualTo(DEFAULT_DESIGN_COORD);
        assertThat(testBuckleArrestorHist.getKpStart()).isEqualTo(DEFAULT_KP_START);
        assertThat(testBuckleArrestorHist.getKpEnd()).isEqualTo(DEFAULT_KP_END);
        assertThat(testBuckleArrestorHist.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testBuckleArrestorHist.getIdStatus()).isEqualTo(DEFAULT_ID_STATUS);
        assertThat(testBuckleArrestorHist.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testBuckleArrestorHist.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testBuckleArrestorHist.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testBuckleArrestorHist.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testBuckleArrestorHist.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testBuckleArrestorHist.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createBuckleArrestorHistWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = buckleArrestorHistRepository.findAll().size();

        // Create the BuckleArrestorHist with an existing ID
        buckleArrestorHist.setId(1L);
        BuckleArrestorHistDTO buckleArrestorHistDTO = buckleArrestorHistMapper.toDto(buckleArrestorHist);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBuckleArrestorHistMockMvc.perform(post("/api/buckle-arrestor-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buckleArrestorHistDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BuckleArrestorHist in the database
        List<BuckleArrestorHist> buckleArrestorHistList = buckleArrestorHistRepository.findAll();
        assertThat(buckleArrestorHistList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDateFromIsRequired() throws Exception {
        int databaseSizeBeforeTest = buckleArrestorHistRepository.findAll().size();
        // set the field null
        buckleArrestorHist.setDateFrom(null);

        // Create the BuckleArrestorHist, which fails.
        BuckleArrestorHistDTO buckleArrestorHistDTO = buckleArrestorHistMapper.toDto(buckleArrestorHist);

        restBuckleArrestorHistMockMvc.perform(post("/api/buckle-arrestor-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buckleArrestorHistDTO)))
            .andExpect(status().isBadRequest());

        List<BuckleArrestorHist> buckleArrestorHistList = buckleArrestorHistRepository.findAll();
        assertThat(buckleArrestorHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateToIsRequired() throws Exception {
        int databaseSizeBeforeTest = buckleArrestorHistRepository.findAll().size();
        // set the field null
        buckleArrestorHist.setDateTo(null);

        // Create the BuckleArrestorHist, which fails.
        BuckleArrestorHistDTO buckleArrestorHistDTO = buckleArrestorHistMapper.toDto(buckleArrestorHist);

        restBuckleArrestorHistMockMvc.perform(post("/api/buckle-arrestor-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buckleArrestorHistDTO)))
            .andExpect(status().isBadRequest());

        List<BuckleArrestorHist> buckleArrestorHistList = buckleArrestorHistRepository.findAll();
        assertThat(buckleArrestorHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDiameterOuterSteelIsRequired() throws Exception {
        int databaseSizeBeforeTest = buckleArrestorHistRepository.findAll().size();
        // set the field null
        buckleArrestorHist.setDiameterOuterSteel(null);

        // Create the BuckleArrestorHist, which fails.
        BuckleArrestorHistDTO buckleArrestorHistDTO = buckleArrestorHistMapper.toDto(buckleArrestorHist);

        restBuckleArrestorHistMockMvc.perform(post("/api/buckle-arrestor-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buckleArrestorHistDTO)))
            .andExpect(status().isBadRequest());

        List<BuckleArrestorHist> buckleArrestorHistList = buckleArrestorHistRepository.findAll();
        assertThat(buckleArrestorHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = buckleArrestorHistRepository.findAll().size();
        // set the field null
        buckleArrestorHist.setIsCurrentFlag(null);

        // Create the BuckleArrestorHist, which fails.
        BuckleArrestorHistDTO buckleArrestorHistDTO = buckleArrestorHistMapper.toDto(buckleArrestorHist);

        restBuckleArrestorHistMockMvc.perform(post("/api/buckle-arrestor-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buckleArrestorHistDTO)))
            .andExpect(status().isBadRequest());

        List<BuckleArrestorHist> buckleArrestorHistList = buckleArrestorHistRepository.findAll();
        assertThat(buckleArrestorHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = buckleArrestorHistRepository.findAll().size();
        // set the field null
        buckleArrestorHist.setIdStatus(null);

        // Create the BuckleArrestorHist, which fails.
        BuckleArrestorHistDTO buckleArrestorHistDTO = buckleArrestorHistMapper.toDto(buckleArrestorHist);

        restBuckleArrestorHistMockMvc.perform(post("/api/buckle-arrestor-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buckleArrestorHistDTO)))
            .andExpect(status().isBadRequest());

        List<BuckleArrestorHist> buckleArrestorHistList = buckleArrestorHistRepository.findAll();
        assertThat(buckleArrestorHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHists() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList
        restBuckleArrestorHistMockMvc.perform(get("/api/buckle-arrestor-hists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(buckleArrestorHist.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateFrom").value(hasItem(DEFAULT_DATE_FROM.toString())))
            .andExpect(jsonPath("$.[*].dateTo").value(hasItem(DEFAULT_DATE_TO.toString())))
            .andExpect(jsonPath("$.[*].idWrk").value(hasItem(DEFAULT_ID_WRK.intValue())))
            .andExpect(jsonPath("$.[*].serialNum").value(hasItem(DEFAULT_SERIAL_NUM.toString())))
            .andExpect(jsonPath("$.[*].diameterOuterSteel").value(hasItem(DEFAULT_DIAMETER_OUTER_STEEL)))
            .andExpect(jsonPath("$.[*].internalCoatThickness").value(hasItem(DEFAULT_INTERNAL_COAT_THICKNESS.intValue())))
            .andExpect(jsonPath("$.[*].externalCoatThickness").value(hasItem(DEFAULT_EXTERNAL_COAT_THICKNESS.intValue())))
            .andExpect(jsonPath("$.[*].isConcrCoating").value(hasItem(DEFAULT_IS_CONCR_COATING)))
            .andExpect(jsonPath("$.[*].concrCoatThickness").value(hasItem(DEFAULT_CONCR_COAT_THICKNESS.intValue())))
            .andExpect(jsonPath("$.[*].concrCoatDensity").value(hasItem(DEFAULT_CONCR_COAT_DENSITY.intValue())))
            .andExpect(jsonPath("$.[*].measColWallThickness").value(hasItem(DEFAULT_MEAS_COL_WALL_THICKNESS.intValue())))
            .andExpect(jsonPath("$.[*].measPipeWallThickness").value(hasItem(DEFAULT_MEAS_PIPE_WALL_THICKNESS.intValue())))
            .andExpect(jsonPath("$.[*].colLength").value(hasItem(DEFAULT_COL_LENGTH)))
            .andExpect(jsonPath("$.[*].pipeLength").value(hasItem(DEFAULT_PIPE_LENGTH)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].smts").value(hasItem(DEFAULT_SMTS.intValue())))
            .andExpect(jsonPath("$.[*].smys").value(hasItem(DEFAULT_SMYS.intValue())))
            .andExpect(jsonPath("$.[*].sdbm").value(hasItem(DEFAULT_SDBM.intValue())))
            .andExpect(jsonPath("$.[*].sdaf").value(hasItem(DEFAULT_SDAF.intValue())))
            .andExpect(jsonPath("$.[*].sdcs").value(hasItem(DEFAULT_SDCS.intValue())))
            .andExpect(jsonPath("$.[*].allowTensStrain").value(hasItem(DEFAULT_ALLOW_TENS_STRAIN.intValue())))
            .andExpect(jsonPath("$.[*].corrosionAllowance").value(hasItem(DEFAULT_CORROSION_ALLOWANCE)))
            .andExpect(jsonPath("$.[*].fabricationAllowance").value(hasItem(DEFAULT_FABRICATION_ALLOWANCE)))
            .andExpect(jsonPath("$.[*].isBurial").value(hasItem(DEFAULT_IS_BURIAL)))
            .andExpect(jsonPath("$.[*].burialDepth").value(hasItem(DEFAULT_BURIAL_DEPTH)))
            .andExpect(jsonPath("$.[*].factBurialDepth").value(hasItem(DEFAULT_FACT_BURIAL_DEPTH)))
            .andExpect(jsonPath("$.[*].dateManufactured").value(hasItem(DEFAULT_DATE_MANUFACTURED.toString())))
            .andExpect(jsonPath("$.[*].millTestPressure").value(hasItem(DEFAULT_MILL_TEST_PRESSURE.intValue())))
            .andExpect(jsonPath("$.[*].designPressure").value(hasItem(DEFAULT_DESIGN_PRESSURE.intValue())))
            .andExpect(jsonPath("$.[*].incidentalPressure").value(hasItem(DEFAULT_INCIDENTAL_PRESSURE.intValue())))
            .andExpect(jsonPath("$.[*].dateInstalled").value(hasItem(DEFAULT_DATE_INSTALLED.toString())))
            .andExpect(jsonPath("$.[*].seamOrient").value(hasItem(DEFAULT_SEAM_ORIENT.intValue())))
            .andExpect(jsonPath("$.[*].seamAngle").value(hasItem(DEFAULT_SEAM_ANGLE.intValue())))
            .andExpect(jsonPath("$.[*].azimuth").value(hasItem(DEFAULT_AZIMUTH.intValue())))
            .andExpect(jsonPath("$.[*].seabInstallTemp").value(hasItem(DEFAULT_SEAB_INSTALL_TEMP.intValue())))
            .andExpect(jsonPath("$.[*].verticalAngle").value(hasItem(DEFAULT_VERTICAL_ANGLE.intValue())))
            .andExpect(jsonPath("$.[*].heatTreatDurat").value(hasItem(DEFAULT_HEAT_TREAT_DURAT.intValue())))
            .andExpect(jsonPath("$.[*].maxDesignTemp").value(hasItem(DEFAULT_MAX_DESIGN_TEMP.intValue())))
            .andExpect(jsonPath("$.[*].heatNumber").value(hasItem(DEFAULT_HEAT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].coord").value(hasItem(DEFAULT_COORD.toString())))
            .andExpect(jsonPath("$.[*].designCoord").value(hasItem(DEFAULT_DESIGN_COORD.intValue())))
            .andExpect(jsonPath("$.[*].kpStart").value(hasItem(DEFAULT_KP_START.intValue())))
            .andExpect(jsonPath("$.[*].kpEnd").value(hasItem(DEFAULT_KP_END.intValue())))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].idStatus").value(hasItem(DEFAULT_ID_STATUS.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getBuckleArrestorHist() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get the buckleArrestorHist
        restBuckleArrestorHistMockMvc.perform(get("/api/buckle-arrestor-hists/{id}", buckleArrestorHist.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(buckleArrestorHist.getId().intValue()))
            .andExpect(jsonPath("$.dateFrom").value(DEFAULT_DATE_FROM.toString()))
            .andExpect(jsonPath("$.dateTo").value(DEFAULT_DATE_TO.toString()))
            .andExpect(jsonPath("$.idWrk").value(DEFAULT_ID_WRK.intValue()))
            .andExpect(jsonPath("$.serialNum").value(DEFAULT_SERIAL_NUM.toString()))
            .andExpect(jsonPath("$.diameterOuterSteel").value(DEFAULT_DIAMETER_OUTER_STEEL))
            .andExpect(jsonPath("$.internalCoatThickness").value(DEFAULT_INTERNAL_COAT_THICKNESS.intValue()))
            .andExpect(jsonPath("$.externalCoatThickness").value(DEFAULT_EXTERNAL_COAT_THICKNESS.intValue()))
            .andExpect(jsonPath("$.isConcrCoating").value(DEFAULT_IS_CONCR_COATING))
            .andExpect(jsonPath("$.concrCoatThickness").value(DEFAULT_CONCR_COAT_THICKNESS.intValue()))
            .andExpect(jsonPath("$.concrCoatDensity").value(DEFAULT_CONCR_COAT_DENSITY.intValue()))
            .andExpect(jsonPath("$.measColWallThickness").value(DEFAULT_MEAS_COL_WALL_THICKNESS.intValue()))
            .andExpect(jsonPath("$.measPipeWallThickness").value(DEFAULT_MEAS_PIPE_WALL_THICKNESS.intValue()))
            .andExpect(jsonPath("$.colLength").value(DEFAULT_COL_LENGTH))
            .andExpect(jsonPath("$.pipeLength").value(DEFAULT_PIPE_LENGTH))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT.intValue()))
            .andExpect(jsonPath("$.smts").value(DEFAULT_SMTS.intValue()))
            .andExpect(jsonPath("$.smys").value(DEFAULT_SMYS.intValue()))
            .andExpect(jsonPath("$.sdbm").value(DEFAULT_SDBM.intValue()))
            .andExpect(jsonPath("$.sdaf").value(DEFAULT_SDAF.intValue()))
            .andExpect(jsonPath("$.sdcs").value(DEFAULT_SDCS.intValue()))
            .andExpect(jsonPath("$.allowTensStrain").value(DEFAULT_ALLOW_TENS_STRAIN.intValue()))
            .andExpect(jsonPath("$.corrosionAllowance").value(DEFAULT_CORROSION_ALLOWANCE))
            .andExpect(jsonPath("$.fabricationAllowance").value(DEFAULT_FABRICATION_ALLOWANCE))
            .andExpect(jsonPath("$.isBurial").value(DEFAULT_IS_BURIAL))
            .andExpect(jsonPath("$.burialDepth").value(DEFAULT_BURIAL_DEPTH))
            .andExpect(jsonPath("$.factBurialDepth").value(DEFAULT_FACT_BURIAL_DEPTH))
            .andExpect(jsonPath("$.dateManufactured").value(DEFAULT_DATE_MANUFACTURED.toString()))
            .andExpect(jsonPath("$.millTestPressure").value(DEFAULT_MILL_TEST_PRESSURE.intValue()))
            .andExpect(jsonPath("$.designPressure").value(DEFAULT_DESIGN_PRESSURE.intValue()))
            .andExpect(jsonPath("$.incidentalPressure").value(DEFAULT_INCIDENTAL_PRESSURE.intValue()))
            .andExpect(jsonPath("$.dateInstalled").value(DEFAULT_DATE_INSTALLED.toString()))
            .andExpect(jsonPath("$.seamOrient").value(DEFAULT_SEAM_ORIENT.intValue()))
            .andExpect(jsonPath("$.seamAngle").value(DEFAULT_SEAM_ANGLE.intValue()))
            .andExpect(jsonPath("$.azimuth").value(DEFAULT_AZIMUTH.intValue()))
            .andExpect(jsonPath("$.seabInstallTemp").value(DEFAULT_SEAB_INSTALL_TEMP.intValue()))
            .andExpect(jsonPath("$.verticalAngle").value(DEFAULT_VERTICAL_ANGLE.intValue()))
            .andExpect(jsonPath("$.heatTreatDurat").value(DEFAULT_HEAT_TREAT_DURAT.intValue()))
            .andExpect(jsonPath("$.maxDesignTemp").value(DEFAULT_MAX_DESIGN_TEMP.intValue()))
            .andExpect(jsonPath("$.heatNumber").value(DEFAULT_HEAT_NUMBER.toString()))
            .andExpect(jsonPath("$.coord").value(DEFAULT_COORD.toString()))
            .andExpect(jsonPath("$.designCoord").value(DEFAULT_DESIGN_COORD.intValue()))
            .andExpect(jsonPath("$.kpStart").value(DEFAULT_KP_START.intValue()))
            .andExpect(jsonPath("$.kpEnd").value(DEFAULT_KP_END.intValue()))
            .andExpect(jsonPath("$.isCurrentFlag").value(DEFAULT_IS_CURRENT_FLAG))
            .andExpect(jsonPath("$.idStatus").value(DEFAULT_ID_STATUS.intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT.toString()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDateFromIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where dateFrom equals to DEFAULT_DATE_FROM
        defaultBuckleArrestorHistShouldBeFound("dateFrom.equals=" + DEFAULT_DATE_FROM);

        // Get all the buckleArrestorHistList where dateFrom equals to UPDATED_DATE_FROM
        defaultBuckleArrestorHistShouldNotBeFound("dateFrom.equals=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDateFromIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where dateFrom in DEFAULT_DATE_FROM or UPDATED_DATE_FROM
        defaultBuckleArrestorHistShouldBeFound("dateFrom.in=" + DEFAULT_DATE_FROM + "," + UPDATED_DATE_FROM);

        // Get all the buckleArrestorHistList where dateFrom equals to UPDATED_DATE_FROM
        defaultBuckleArrestorHistShouldNotBeFound("dateFrom.in=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDateFromIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where dateFrom is not null
        defaultBuckleArrestorHistShouldBeFound("dateFrom.specified=true");

        // Get all the buckleArrestorHistList where dateFrom is null
        defaultBuckleArrestorHistShouldNotBeFound("dateFrom.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDateFromIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where dateFrom greater than or equals to DEFAULT_DATE_FROM
        defaultBuckleArrestorHistShouldBeFound("dateFrom.greaterOrEqualThan=" + DEFAULT_DATE_FROM);

        // Get all the buckleArrestorHistList where dateFrom greater than or equals to UPDATED_DATE_FROM
        defaultBuckleArrestorHistShouldNotBeFound("dateFrom.greaterOrEqualThan=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDateFromIsLessThanSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where dateFrom less than or equals to DEFAULT_DATE_FROM
        defaultBuckleArrestorHistShouldNotBeFound("dateFrom.lessThan=" + DEFAULT_DATE_FROM);

        // Get all the buckleArrestorHistList where dateFrom less than or equals to UPDATED_DATE_FROM
        defaultBuckleArrestorHistShouldBeFound("dateFrom.lessThan=" + UPDATED_DATE_FROM);
    }


    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDateToIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where dateTo equals to DEFAULT_DATE_TO
        defaultBuckleArrestorHistShouldBeFound("dateTo.equals=" + DEFAULT_DATE_TO);

        // Get all the buckleArrestorHistList where dateTo equals to UPDATED_DATE_TO
        defaultBuckleArrestorHistShouldNotBeFound("dateTo.equals=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDateToIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where dateTo in DEFAULT_DATE_TO or UPDATED_DATE_TO
        defaultBuckleArrestorHistShouldBeFound("dateTo.in=" + DEFAULT_DATE_TO + "," + UPDATED_DATE_TO);

        // Get all the buckleArrestorHistList where dateTo equals to UPDATED_DATE_TO
        defaultBuckleArrestorHistShouldNotBeFound("dateTo.in=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDateToIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where dateTo is not null
        defaultBuckleArrestorHistShouldBeFound("dateTo.specified=true");

        // Get all the buckleArrestorHistList where dateTo is null
        defaultBuckleArrestorHistShouldNotBeFound("dateTo.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDateToIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where dateTo greater than or equals to DEFAULT_DATE_TO
        defaultBuckleArrestorHistShouldBeFound("dateTo.greaterOrEqualThan=" + DEFAULT_DATE_TO);

        // Get all the buckleArrestorHistList where dateTo greater than or equals to UPDATED_DATE_TO
        defaultBuckleArrestorHistShouldNotBeFound("dateTo.greaterOrEqualThan=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDateToIsLessThanSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where dateTo less than or equals to DEFAULT_DATE_TO
        defaultBuckleArrestorHistShouldNotBeFound("dateTo.lessThan=" + DEFAULT_DATE_TO);

        // Get all the buckleArrestorHistList where dateTo less than or equals to UPDATED_DATE_TO
        defaultBuckleArrestorHistShouldBeFound("dateTo.lessThan=" + UPDATED_DATE_TO);
    }


    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIdWrkIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where idWrk equals to DEFAULT_ID_WRK
        defaultBuckleArrestorHistShouldBeFound("idWrk.equals=" + DEFAULT_ID_WRK);

        // Get all the buckleArrestorHistList where idWrk equals to UPDATED_ID_WRK
        defaultBuckleArrestorHistShouldNotBeFound("idWrk.equals=" + UPDATED_ID_WRK);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIdWrkIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where idWrk in DEFAULT_ID_WRK or UPDATED_ID_WRK
        defaultBuckleArrestorHistShouldBeFound("idWrk.in=" + DEFAULT_ID_WRK + "," + UPDATED_ID_WRK);

        // Get all the buckleArrestorHistList where idWrk equals to UPDATED_ID_WRK
        defaultBuckleArrestorHistShouldNotBeFound("idWrk.in=" + UPDATED_ID_WRK);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIdWrkIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where idWrk is not null
        defaultBuckleArrestorHistShouldBeFound("idWrk.specified=true");

        // Get all the buckleArrestorHistList where idWrk is null
        defaultBuckleArrestorHistShouldNotBeFound("idWrk.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIdWrkIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where idWrk greater than or equals to DEFAULT_ID_WRK
        defaultBuckleArrestorHistShouldBeFound("idWrk.greaterOrEqualThan=" + DEFAULT_ID_WRK);

        // Get all the buckleArrestorHistList where idWrk greater than or equals to UPDATED_ID_WRK
        defaultBuckleArrestorHistShouldNotBeFound("idWrk.greaterOrEqualThan=" + UPDATED_ID_WRK);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIdWrkIsLessThanSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where idWrk less than or equals to DEFAULT_ID_WRK
        defaultBuckleArrestorHistShouldNotBeFound("idWrk.lessThan=" + DEFAULT_ID_WRK);

        // Get all the buckleArrestorHistList where idWrk less than or equals to UPDATED_ID_WRK
        defaultBuckleArrestorHistShouldBeFound("idWrk.lessThan=" + UPDATED_ID_WRK);
    }


    @Test
    @Transactional
    public void getAllBuckleArrestorHistsBySerialNumIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where serialNum equals to DEFAULT_SERIAL_NUM
        defaultBuckleArrestorHistShouldBeFound("serialNum.equals=" + DEFAULT_SERIAL_NUM);

        // Get all the buckleArrestorHistList where serialNum equals to UPDATED_SERIAL_NUM
        defaultBuckleArrestorHistShouldNotBeFound("serialNum.equals=" + UPDATED_SERIAL_NUM);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsBySerialNumIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where serialNum in DEFAULT_SERIAL_NUM or UPDATED_SERIAL_NUM
        defaultBuckleArrestorHistShouldBeFound("serialNum.in=" + DEFAULT_SERIAL_NUM + "," + UPDATED_SERIAL_NUM);

        // Get all the buckleArrestorHistList where serialNum equals to UPDATED_SERIAL_NUM
        defaultBuckleArrestorHistShouldNotBeFound("serialNum.in=" + UPDATED_SERIAL_NUM);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsBySerialNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where serialNum is not null
        defaultBuckleArrestorHistShouldBeFound("serialNum.specified=true");

        // Get all the buckleArrestorHistList where serialNum is null
        defaultBuckleArrestorHistShouldNotBeFound("serialNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDiameterOuterSteelIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where diameterOuterSteel equals to DEFAULT_DIAMETER_OUTER_STEEL
        defaultBuckleArrestorHistShouldBeFound("diameterOuterSteel.equals=" + DEFAULT_DIAMETER_OUTER_STEEL);

        // Get all the buckleArrestorHistList where diameterOuterSteel equals to UPDATED_DIAMETER_OUTER_STEEL
        defaultBuckleArrestorHistShouldNotBeFound("diameterOuterSteel.equals=" + UPDATED_DIAMETER_OUTER_STEEL);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDiameterOuterSteelIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where diameterOuterSteel in DEFAULT_DIAMETER_OUTER_STEEL or UPDATED_DIAMETER_OUTER_STEEL
        defaultBuckleArrestorHistShouldBeFound("diameterOuterSteel.in=" + DEFAULT_DIAMETER_OUTER_STEEL + "," + UPDATED_DIAMETER_OUTER_STEEL);

        // Get all the buckleArrestorHistList where diameterOuterSteel equals to UPDATED_DIAMETER_OUTER_STEEL
        defaultBuckleArrestorHistShouldNotBeFound("diameterOuterSteel.in=" + UPDATED_DIAMETER_OUTER_STEEL);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDiameterOuterSteelIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where diameterOuterSteel is not null
        defaultBuckleArrestorHistShouldBeFound("diameterOuterSteel.specified=true");

        // Get all the buckleArrestorHistList where diameterOuterSteel is null
        defaultBuckleArrestorHistShouldNotBeFound("diameterOuterSteel.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDiameterOuterSteelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where diameterOuterSteel greater than or equals to DEFAULT_DIAMETER_OUTER_STEEL
        defaultBuckleArrestorHistShouldBeFound("diameterOuterSteel.greaterOrEqualThan=" + DEFAULT_DIAMETER_OUTER_STEEL);

        // Get all the buckleArrestorHistList where diameterOuterSteel greater than or equals to UPDATED_DIAMETER_OUTER_STEEL
        defaultBuckleArrestorHistShouldNotBeFound("diameterOuterSteel.greaterOrEqualThan=" + UPDATED_DIAMETER_OUTER_STEEL);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDiameterOuterSteelIsLessThanSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where diameterOuterSteel less than or equals to DEFAULT_DIAMETER_OUTER_STEEL
        defaultBuckleArrestorHistShouldNotBeFound("diameterOuterSteel.lessThan=" + DEFAULT_DIAMETER_OUTER_STEEL);

        // Get all the buckleArrestorHistList where diameterOuterSteel less than or equals to UPDATED_DIAMETER_OUTER_STEEL
        defaultBuckleArrestorHistShouldBeFound("diameterOuterSteel.lessThan=" + UPDATED_DIAMETER_OUTER_STEEL);
    }


    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByInternalCoatThicknessIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where internalCoatThickness equals to DEFAULT_INTERNAL_COAT_THICKNESS
        defaultBuckleArrestorHistShouldBeFound("internalCoatThickness.equals=" + DEFAULT_INTERNAL_COAT_THICKNESS);

        // Get all the buckleArrestorHistList where internalCoatThickness equals to UPDATED_INTERNAL_COAT_THICKNESS
        defaultBuckleArrestorHistShouldNotBeFound("internalCoatThickness.equals=" + UPDATED_INTERNAL_COAT_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByInternalCoatThicknessIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where internalCoatThickness in DEFAULT_INTERNAL_COAT_THICKNESS or UPDATED_INTERNAL_COAT_THICKNESS
        defaultBuckleArrestorHistShouldBeFound("internalCoatThickness.in=" + DEFAULT_INTERNAL_COAT_THICKNESS + "," + UPDATED_INTERNAL_COAT_THICKNESS);

        // Get all the buckleArrestorHistList where internalCoatThickness equals to UPDATED_INTERNAL_COAT_THICKNESS
        defaultBuckleArrestorHistShouldNotBeFound("internalCoatThickness.in=" + UPDATED_INTERNAL_COAT_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByInternalCoatThicknessIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where internalCoatThickness is not null
        defaultBuckleArrestorHistShouldBeFound("internalCoatThickness.specified=true");

        // Get all the buckleArrestorHistList where internalCoatThickness is null
        defaultBuckleArrestorHistShouldNotBeFound("internalCoatThickness.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByExternalCoatThicknessIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where externalCoatThickness equals to DEFAULT_EXTERNAL_COAT_THICKNESS
        defaultBuckleArrestorHistShouldBeFound("externalCoatThickness.equals=" + DEFAULT_EXTERNAL_COAT_THICKNESS);

        // Get all the buckleArrestorHistList where externalCoatThickness equals to UPDATED_EXTERNAL_COAT_THICKNESS
        defaultBuckleArrestorHistShouldNotBeFound("externalCoatThickness.equals=" + UPDATED_EXTERNAL_COAT_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByExternalCoatThicknessIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where externalCoatThickness in DEFAULT_EXTERNAL_COAT_THICKNESS or UPDATED_EXTERNAL_COAT_THICKNESS
        defaultBuckleArrestorHistShouldBeFound("externalCoatThickness.in=" + DEFAULT_EXTERNAL_COAT_THICKNESS + "," + UPDATED_EXTERNAL_COAT_THICKNESS);

        // Get all the buckleArrestorHistList where externalCoatThickness equals to UPDATED_EXTERNAL_COAT_THICKNESS
        defaultBuckleArrestorHistShouldNotBeFound("externalCoatThickness.in=" + UPDATED_EXTERNAL_COAT_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByExternalCoatThicknessIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where externalCoatThickness is not null
        defaultBuckleArrestorHistShouldBeFound("externalCoatThickness.specified=true");

        // Get all the buckleArrestorHistList where externalCoatThickness is null
        defaultBuckleArrestorHistShouldNotBeFound("externalCoatThickness.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIsConcrCoatingIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where isConcrCoating equals to DEFAULT_IS_CONCR_COATING
        defaultBuckleArrestorHistShouldBeFound("isConcrCoating.equals=" + DEFAULT_IS_CONCR_COATING);

        // Get all the buckleArrestorHistList where isConcrCoating equals to UPDATED_IS_CONCR_COATING
        defaultBuckleArrestorHistShouldNotBeFound("isConcrCoating.equals=" + UPDATED_IS_CONCR_COATING);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIsConcrCoatingIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where isConcrCoating in DEFAULT_IS_CONCR_COATING or UPDATED_IS_CONCR_COATING
        defaultBuckleArrestorHistShouldBeFound("isConcrCoating.in=" + DEFAULT_IS_CONCR_COATING + "," + UPDATED_IS_CONCR_COATING);

        // Get all the buckleArrestorHistList where isConcrCoating equals to UPDATED_IS_CONCR_COATING
        defaultBuckleArrestorHistShouldNotBeFound("isConcrCoating.in=" + UPDATED_IS_CONCR_COATING);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIsConcrCoatingIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where isConcrCoating is not null
        defaultBuckleArrestorHistShouldBeFound("isConcrCoating.specified=true");

        // Get all the buckleArrestorHistList where isConcrCoating is null
        defaultBuckleArrestorHistShouldNotBeFound("isConcrCoating.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIsConcrCoatingIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where isConcrCoating greater than or equals to DEFAULT_IS_CONCR_COATING
        defaultBuckleArrestorHistShouldBeFound("isConcrCoating.greaterOrEqualThan=" + DEFAULT_IS_CONCR_COATING);

        // Get all the buckleArrestorHistList where isConcrCoating greater than or equals to UPDATED_IS_CONCR_COATING
        defaultBuckleArrestorHistShouldNotBeFound("isConcrCoating.greaterOrEqualThan=" + UPDATED_IS_CONCR_COATING);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIsConcrCoatingIsLessThanSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where isConcrCoating less than or equals to DEFAULT_IS_CONCR_COATING
        defaultBuckleArrestorHistShouldNotBeFound("isConcrCoating.lessThan=" + DEFAULT_IS_CONCR_COATING);

        // Get all the buckleArrestorHistList where isConcrCoating less than or equals to UPDATED_IS_CONCR_COATING
        defaultBuckleArrestorHistShouldBeFound("isConcrCoating.lessThan=" + UPDATED_IS_CONCR_COATING);
    }


    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByConcrCoatThicknessIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where concrCoatThickness equals to DEFAULT_CONCR_COAT_THICKNESS
        defaultBuckleArrestorHistShouldBeFound("concrCoatThickness.equals=" + DEFAULT_CONCR_COAT_THICKNESS);

        // Get all the buckleArrestorHistList where concrCoatThickness equals to UPDATED_CONCR_COAT_THICKNESS
        defaultBuckleArrestorHistShouldNotBeFound("concrCoatThickness.equals=" + UPDATED_CONCR_COAT_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByConcrCoatThicknessIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where concrCoatThickness in DEFAULT_CONCR_COAT_THICKNESS or UPDATED_CONCR_COAT_THICKNESS
        defaultBuckleArrestorHistShouldBeFound("concrCoatThickness.in=" + DEFAULT_CONCR_COAT_THICKNESS + "," + UPDATED_CONCR_COAT_THICKNESS);

        // Get all the buckleArrestorHistList where concrCoatThickness equals to UPDATED_CONCR_COAT_THICKNESS
        defaultBuckleArrestorHistShouldNotBeFound("concrCoatThickness.in=" + UPDATED_CONCR_COAT_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByConcrCoatThicknessIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where concrCoatThickness is not null
        defaultBuckleArrestorHistShouldBeFound("concrCoatThickness.specified=true");

        // Get all the buckleArrestorHistList where concrCoatThickness is null
        defaultBuckleArrestorHistShouldNotBeFound("concrCoatThickness.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByConcrCoatDensityIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where concrCoatDensity equals to DEFAULT_CONCR_COAT_DENSITY
        defaultBuckleArrestorHistShouldBeFound("concrCoatDensity.equals=" + DEFAULT_CONCR_COAT_DENSITY);

        // Get all the buckleArrestorHistList where concrCoatDensity equals to UPDATED_CONCR_COAT_DENSITY
        defaultBuckleArrestorHistShouldNotBeFound("concrCoatDensity.equals=" + UPDATED_CONCR_COAT_DENSITY);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByConcrCoatDensityIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where concrCoatDensity in DEFAULT_CONCR_COAT_DENSITY or UPDATED_CONCR_COAT_DENSITY
        defaultBuckleArrestorHistShouldBeFound("concrCoatDensity.in=" + DEFAULT_CONCR_COAT_DENSITY + "," + UPDATED_CONCR_COAT_DENSITY);

        // Get all the buckleArrestorHistList where concrCoatDensity equals to UPDATED_CONCR_COAT_DENSITY
        defaultBuckleArrestorHistShouldNotBeFound("concrCoatDensity.in=" + UPDATED_CONCR_COAT_DENSITY);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByConcrCoatDensityIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where concrCoatDensity is not null
        defaultBuckleArrestorHistShouldBeFound("concrCoatDensity.specified=true");

        // Get all the buckleArrestorHistList where concrCoatDensity is null
        defaultBuckleArrestorHistShouldNotBeFound("concrCoatDensity.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByMeasColWallThicknessIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where measColWallThickness equals to DEFAULT_MEAS_COL_WALL_THICKNESS
        defaultBuckleArrestorHistShouldBeFound("measColWallThickness.equals=" + DEFAULT_MEAS_COL_WALL_THICKNESS);

        // Get all the buckleArrestorHistList where measColWallThickness equals to UPDATED_MEAS_COL_WALL_THICKNESS
        defaultBuckleArrestorHistShouldNotBeFound("measColWallThickness.equals=" + UPDATED_MEAS_COL_WALL_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByMeasColWallThicknessIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where measColWallThickness in DEFAULT_MEAS_COL_WALL_THICKNESS or UPDATED_MEAS_COL_WALL_THICKNESS
        defaultBuckleArrestorHistShouldBeFound("measColWallThickness.in=" + DEFAULT_MEAS_COL_WALL_THICKNESS + "," + UPDATED_MEAS_COL_WALL_THICKNESS);

        // Get all the buckleArrestorHistList where measColWallThickness equals to UPDATED_MEAS_COL_WALL_THICKNESS
        defaultBuckleArrestorHistShouldNotBeFound("measColWallThickness.in=" + UPDATED_MEAS_COL_WALL_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByMeasColWallThicknessIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where measColWallThickness is not null
        defaultBuckleArrestorHistShouldBeFound("measColWallThickness.specified=true");

        // Get all the buckleArrestorHistList where measColWallThickness is null
        defaultBuckleArrestorHistShouldNotBeFound("measColWallThickness.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByMeasPipeWallThicknessIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where measPipeWallThickness equals to DEFAULT_MEAS_PIPE_WALL_THICKNESS
        defaultBuckleArrestorHistShouldBeFound("measPipeWallThickness.equals=" + DEFAULT_MEAS_PIPE_WALL_THICKNESS);

        // Get all the buckleArrestorHistList where measPipeWallThickness equals to UPDATED_MEAS_PIPE_WALL_THICKNESS
        defaultBuckleArrestorHistShouldNotBeFound("measPipeWallThickness.equals=" + UPDATED_MEAS_PIPE_WALL_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByMeasPipeWallThicknessIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where measPipeWallThickness in DEFAULT_MEAS_PIPE_WALL_THICKNESS or UPDATED_MEAS_PIPE_WALL_THICKNESS
        defaultBuckleArrestorHistShouldBeFound("measPipeWallThickness.in=" + DEFAULT_MEAS_PIPE_WALL_THICKNESS + "," + UPDATED_MEAS_PIPE_WALL_THICKNESS);

        // Get all the buckleArrestorHistList where measPipeWallThickness equals to UPDATED_MEAS_PIPE_WALL_THICKNESS
        defaultBuckleArrestorHistShouldNotBeFound("measPipeWallThickness.in=" + UPDATED_MEAS_PIPE_WALL_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByMeasPipeWallThicknessIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where measPipeWallThickness is not null
        defaultBuckleArrestorHistShouldBeFound("measPipeWallThickness.specified=true");

        // Get all the buckleArrestorHistList where measPipeWallThickness is null
        defaultBuckleArrestorHistShouldNotBeFound("measPipeWallThickness.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByColLengthIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where colLength equals to DEFAULT_COL_LENGTH
        defaultBuckleArrestorHistShouldBeFound("colLength.equals=" + DEFAULT_COL_LENGTH);

        // Get all the buckleArrestorHistList where colLength equals to UPDATED_COL_LENGTH
        defaultBuckleArrestorHistShouldNotBeFound("colLength.equals=" + UPDATED_COL_LENGTH);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByColLengthIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where colLength in DEFAULT_COL_LENGTH or UPDATED_COL_LENGTH
        defaultBuckleArrestorHistShouldBeFound("colLength.in=" + DEFAULT_COL_LENGTH + "," + UPDATED_COL_LENGTH);

        // Get all the buckleArrestorHistList where colLength equals to UPDATED_COL_LENGTH
        defaultBuckleArrestorHistShouldNotBeFound("colLength.in=" + UPDATED_COL_LENGTH);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByColLengthIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where colLength is not null
        defaultBuckleArrestorHistShouldBeFound("colLength.specified=true");

        // Get all the buckleArrestorHistList where colLength is null
        defaultBuckleArrestorHistShouldNotBeFound("colLength.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByColLengthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where colLength greater than or equals to DEFAULT_COL_LENGTH
        defaultBuckleArrestorHistShouldBeFound("colLength.greaterOrEqualThan=" + DEFAULT_COL_LENGTH);

        // Get all the buckleArrestorHistList where colLength greater than or equals to UPDATED_COL_LENGTH
        defaultBuckleArrestorHistShouldNotBeFound("colLength.greaterOrEqualThan=" + UPDATED_COL_LENGTH);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByColLengthIsLessThanSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where colLength less than or equals to DEFAULT_COL_LENGTH
        defaultBuckleArrestorHistShouldNotBeFound("colLength.lessThan=" + DEFAULT_COL_LENGTH);

        // Get all the buckleArrestorHistList where colLength less than or equals to UPDATED_COL_LENGTH
        defaultBuckleArrestorHistShouldBeFound("colLength.lessThan=" + UPDATED_COL_LENGTH);
    }


    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByPipeLengthIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where pipeLength equals to DEFAULT_PIPE_LENGTH
        defaultBuckleArrestorHistShouldBeFound("pipeLength.equals=" + DEFAULT_PIPE_LENGTH);

        // Get all the buckleArrestorHistList where pipeLength equals to UPDATED_PIPE_LENGTH
        defaultBuckleArrestorHistShouldNotBeFound("pipeLength.equals=" + UPDATED_PIPE_LENGTH);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByPipeLengthIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where pipeLength in DEFAULT_PIPE_LENGTH or UPDATED_PIPE_LENGTH
        defaultBuckleArrestorHistShouldBeFound("pipeLength.in=" + DEFAULT_PIPE_LENGTH + "," + UPDATED_PIPE_LENGTH);

        // Get all the buckleArrestorHistList where pipeLength equals to UPDATED_PIPE_LENGTH
        defaultBuckleArrestorHistShouldNotBeFound("pipeLength.in=" + UPDATED_PIPE_LENGTH);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByPipeLengthIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where pipeLength is not null
        defaultBuckleArrestorHistShouldBeFound("pipeLength.specified=true");

        // Get all the buckleArrestorHistList where pipeLength is null
        defaultBuckleArrestorHistShouldNotBeFound("pipeLength.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByPipeLengthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where pipeLength greater than or equals to DEFAULT_PIPE_LENGTH
        defaultBuckleArrestorHistShouldBeFound("pipeLength.greaterOrEqualThan=" + DEFAULT_PIPE_LENGTH);

        // Get all the buckleArrestorHistList where pipeLength greater than or equals to UPDATED_PIPE_LENGTH
        defaultBuckleArrestorHistShouldNotBeFound("pipeLength.greaterOrEqualThan=" + UPDATED_PIPE_LENGTH);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByPipeLengthIsLessThanSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where pipeLength less than or equals to DEFAULT_PIPE_LENGTH
        defaultBuckleArrestorHistShouldNotBeFound("pipeLength.lessThan=" + DEFAULT_PIPE_LENGTH);

        // Get all the buckleArrestorHistList where pipeLength less than or equals to UPDATED_PIPE_LENGTH
        defaultBuckleArrestorHistShouldBeFound("pipeLength.lessThan=" + UPDATED_PIPE_LENGTH);
    }


    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByWeightIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where weight equals to DEFAULT_WEIGHT
        defaultBuckleArrestorHistShouldBeFound("weight.equals=" + DEFAULT_WEIGHT);

        // Get all the buckleArrestorHistList where weight equals to UPDATED_WEIGHT
        defaultBuckleArrestorHistShouldNotBeFound("weight.equals=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByWeightIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where weight in DEFAULT_WEIGHT or UPDATED_WEIGHT
        defaultBuckleArrestorHistShouldBeFound("weight.in=" + DEFAULT_WEIGHT + "," + UPDATED_WEIGHT);

        // Get all the buckleArrestorHistList where weight equals to UPDATED_WEIGHT
        defaultBuckleArrestorHistShouldNotBeFound("weight.in=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByWeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where weight is not null
        defaultBuckleArrestorHistShouldBeFound("weight.specified=true");

        // Get all the buckleArrestorHistList where weight is null
        defaultBuckleArrestorHistShouldNotBeFound("weight.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsBySmtsIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where smts equals to DEFAULT_SMTS
        defaultBuckleArrestorHistShouldBeFound("smts.equals=" + DEFAULT_SMTS);

        // Get all the buckleArrestorHistList where smts equals to UPDATED_SMTS
        defaultBuckleArrestorHistShouldNotBeFound("smts.equals=" + UPDATED_SMTS);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsBySmtsIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where smts in DEFAULT_SMTS or UPDATED_SMTS
        defaultBuckleArrestorHistShouldBeFound("smts.in=" + DEFAULT_SMTS + "," + UPDATED_SMTS);

        // Get all the buckleArrestorHistList where smts equals to UPDATED_SMTS
        defaultBuckleArrestorHistShouldNotBeFound("smts.in=" + UPDATED_SMTS);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsBySmtsIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where smts is not null
        defaultBuckleArrestorHistShouldBeFound("smts.specified=true");

        // Get all the buckleArrestorHistList where smts is null
        defaultBuckleArrestorHistShouldNotBeFound("smts.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsBySmysIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where smys equals to DEFAULT_SMYS
        defaultBuckleArrestorHistShouldBeFound("smys.equals=" + DEFAULT_SMYS);

        // Get all the buckleArrestorHistList where smys equals to UPDATED_SMYS
        defaultBuckleArrestorHistShouldNotBeFound("smys.equals=" + UPDATED_SMYS);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsBySmysIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where smys in DEFAULT_SMYS or UPDATED_SMYS
        defaultBuckleArrestorHistShouldBeFound("smys.in=" + DEFAULT_SMYS + "," + UPDATED_SMYS);

        // Get all the buckleArrestorHistList where smys equals to UPDATED_SMYS
        defaultBuckleArrestorHistShouldNotBeFound("smys.in=" + UPDATED_SMYS);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsBySmysIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where smys is not null
        defaultBuckleArrestorHistShouldBeFound("smys.specified=true");

        // Get all the buckleArrestorHistList where smys is null
        defaultBuckleArrestorHistShouldNotBeFound("smys.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsBySdbmIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where sdbm equals to DEFAULT_SDBM
        defaultBuckleArrestorHistShouldBeFound("sdbm.equals=" + DEFAULT_SDBM);

        // Get all the buckleArrestorHistList where sdbm equals to UPDATED_SDBM
        defaultBuckleArrestorHistShouldNotBeFound("sdbm.equals=" + UPDATED_SDBM);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsBySdbmIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where sdbm in DEFAULT_SDBM or UPDATED_SDBM
        defaultBuckleArrestorHistShouldBeFound("sdbm.in=" + DEFAULT_SDBM + "," + UPDATED_SDBM);

        // Get all the buckleArrestorHistList where sdbm equals to UPDATED_SDBM
        defaultBuckleArrestorHistShouldNotBeFound("sdbm.in=" + UPDATED_SDBM);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsBySdbmIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where sdbm is not null
        defaultBuckleArrestorHistShouldBeFound("sdbm.specified=true");

        // Get all the buckleArrestorHistList where sdbm is null
        defaultBuckleArrestorHistShouldNotBeFound("sdbm.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsBySdafIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where sdaf equals to DEFAULT_SDAF
        defaultBuckleArrestorHistShouldBeFound("sdaf.equals=" + DEFAULT_SDAF);

        // Get all the buckleArrestorHistList where sdaf equals to UPDATED_SDAF
        defaultBuckleArrestorHistShouldNotBeFound("sdaf.equals=" + UPDATED_SDAF);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsBySdafIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where sdaf in DEFAULT_SDAF or UPDATED_SDAF
        defaultBuckleArrestorHistShouldBeFound("sdaf.in=" + DEFAULT_SDAF + "," + UPDATED_SDAF);

        // Get all the buckleArrestorHistList where sdaf equals to UPDATED_SDAF
        defaultBuckleArrestorHistShouldNotBeFound("sdaf.in=" + UPDATED_SDAF);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsBySdafIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where sdaf is not null
        defaultBuckleArrestorHistShouldBeFound("sdaf.specified=true");

        // Get all the buckleArrestorHistList where sdaf is null
        defaultBuckleArrestorHistShouldNotBeFound("sdaf.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsBySdcsIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where sdcs equals to DEFAULT_SDCS
        defaultBuckleArrestorHistShouldBeFound("sdcs.equals=" + DEFAULT_SDCS);

        // Get all the buckleArrestorHistList where sdcs equals to UPDATED_SDCS
        defaultBuckleArrestorHistShouldNotBeFound("sdcs.equals=" + UPDATED_SDCS);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsBySdcsIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where sdcs in DEFAULT_SDCS or UPDATED_SDCS
        defaultBuckleArrestorHistShouldBeFound("sdcs.in=" + DEFAULT_SDCS + "," + UPDATED_SDCS);

        // Get all the buckleArrestorHistList where sdcs equals to UPDATED_SDCS
        defaultBuckleArrestorHistShouldNotBeFound("sdcs.in=" + UPDATED_SDCS);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsBySdcsIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where sdcs is not null
        defaultBuckleArrestorHistShouldBeFound("sdcs.specified=true");

        // Get all the buckleArrestorHistList where sdcs is null
        defaultBuckleArrestorHistShouldNotBeFound("sdcs.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByAllowTensStrainIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where allowTensStrain equals to DEFAULT_ALLOW_TENS_STRAIN
        defaultBuckleArrestorHistShouldBeFound("allowTensStrain.equals=" + DEFAULT_ALLOW_TENS_STRAIN);

        // Get all the buckleArrestorHistList where allowTensStrain equals to UPDATED_ALLOW_TENS_STRAIN
        defaultBuckleArrestorHistShouldNotBeFound("allowTensStrain.equals=" + UPDATED_ALLOW_TENS_STRAIN);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByAllowTensStrainIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where allowTensStrain in DEFAULT_ALLOW_TENS_STRAIN or UPDATED_ALLOW_TENS_STRAIN
        defaultBuckleArrestorHistShouldBeFound("allowTensStrain.in=" + DEFAULT_ALLOW_TENS_STRAIN + "," + UPDATED_ALLOW_TENS_STRAIN);

        // Get all the buckleArrestorHistList where allowTensStrain equals to UPDATED_ALLOW_TENS_STRAIN
        defaultBuckleArrestorHistShouldNotBeFound("allowTensStrain.in=" + UPDATED_ALLOW_TENS_STRAIN);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByAllowTensStrainIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where allowTensStrain is not null
        defaultBuckleArrestorHistShouldBeFound("allowTensStrain.specified=true");

        // Get all the buckleArrestorHistList where allowTensStrain is null
        defaultBuckleArrestorHistShouldNotBeFound("allowTensStrain.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByCorrosionAllowanceIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where corrosionAllowance equals to DEFAULT_CORROSION_ALLOWANCE
        defaultBuckleArrestorHistShouldBeFound("corrosionAllowance.equals=" + DEFAULT_CORROSION_ALLOWANCE);

        // Get all the buckleArrestorHistList where corrosionAllowance equals to UPDATED_CORROSION_ALLOWANCE
        defaultBuckleArrestorHistShouldNotBeFound("corrosionAllowance.equals=" + UPDATED_CORROSION_ALLOWANCE);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByCorrosionAllowanceIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where corrosionAllowance in DEFAULT_CORROSION_ALLOWANCE or UPDATED_CORROSION_ALLOWANCE
        defaultBuckleArrestorHistShouldBeFound("corrosionAllowance.in=" + DEFAULT_CORROSION_ALLOWANCE + "," + UPDATED_CORROSION_ALLOWANCE);

        // Get all the buckleArrestorHistList where corrosionAllowance equals to UPDATED_CORROSION_ALLOWANCE
        defaultBuckleArrestorHistShouldNotBeFound("corrosionAllowance.in=" + UPDATED_CORROSION_ALLOWANCE);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByCorrosionAllowanceIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where corrosionAllowance is not null
        defaultBuckleArrestorHistShouldBeFound("corrosionAllowance.specified=true");

        // Get all the buckleArrestorHistList where corrosionAllowance is null
        defaultBuckleArrestorHistShouldNotBeFound("corrosionAllowance.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByCorrosionAllowanceIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where corrosionAllowance greater than or equals to DEFAULT_CORROSION_ALLOWANCE
        defaultBuckleArrestorHistShouldBeFound("corrosionAllowance.greaterOrEqualThan=" + DEFAULT_CORROSION_ALLOWANCE);

        // Get all the buckleArrestorHistList where corrosionAllowance greater than or equals to UPDATED_CORROSION_ALLOWANCE
        defaultBuckleArrestorHistShouldNotBeFound("corrosionAllowance.greaterOrEqualThan=" + UPDATED_CORROSION_ALLOWANCE);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByCorrosionAllowanceIsLessThanSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where corrosionAllowance less than or equals to DEFAULT_CORROSION_ALLOWANCE
        defaultBuckleArrestorHistShouldNotBeFound("corrosionAllowance.lessThan=" + DEFAULT_CORROSION_ALLOWANCE);

        // Get all the buckleArrestorHistList where corrosionAllowance less than or equals to UPDATED_CORROSION_ALLOWANCE
        defaultBuckleArrestorHistShouldBeFound("corrosionAllowance.lessThan=" + UPDATED_CORROSION_ALLOWANCE);
    }


    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByFabricationAllowanceIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where fabricationAllowance equals to DEFAULT_FABRICATION_ALLOWANCE
        defaultBuckleArrestorHistShouldBeFound("fabricationAllowance.equals=" + DEFAULT_FABRICATION_ALLOWANCE);

        // Get all the buckleArrestorHistList where fabricationAllowance equals to UPDATED_FABRICATION_ALLOWANCE
        defaultBuckleArrestorHistShouldNotBeFound("fabricationAllowance.equals=" + UPDATED_FABRICATION_ALLOWANCE);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByFabricationAllowanceIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where fabricationAllowance in DEFAULT_FABRICATION_ALLOWANCE or UPDATED_FABRICATION_ALLOWANCE
        defaultBuckleArrestorHistShouldBeFound("fabricationAllowance.in=" + DEFAULT_FABRICATION_ALLOWANCE + "," + UPDATED_FABRICATION_ALLOWANCE);

        // Get all the buckleArrestorHistList where fabricationAllowance equals to UPDATED_FABRICATION_ALLOWANCE
        defaultBuckleArrestorHistShouldNotBeFound("fabricationAllowance.in=" + UPDATED_FABRICATION_ALLOWANCE);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByFabricationAllowanceIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where fabricationAllowance is not null
        defaultBuckleArrestorHistShouldBeFound("fabricationAllowance.specified=true");

        // Get all the buckleArrestorHistList where fabricationAllowance is null
        defaultBuckleArrestorHistShouldNotBeFound("fabricationAllowance.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByFabricationAllowanceIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where fabricationAllowance greater than or equals to DEFAULT_FABRICATION_ALLOWANCE
        defaultBuckleArrestorHistShouldBeFound("fabricationAllowance.greaterOrEqualThan=" + DEFAULT_FABRICATION_ALLOWANCE);

        // Get all the buckleArrestorHistList where fabricationAllowance greater than or equals to UPDATED_FABRICATION_ALLOWANCE
        defaultBuckleArrestorHistShouldNotBeFound("fabricationAllowance.greaterOrEqualThan=" + UPDATED_FABRICATION_ALLOWANCE);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByFabricationAllowanceIsLessThanSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where fabricationAllowance less than or equals to DEFAULT_FABRICATION_ALLOWANCE
        defaultBuckleArrestorHistShouldNotBeFound("fabricationAllowance.lessThan=" + DEFAULT_FABRICATION_ALLOWANCE);

        // Get all the buckleArrestorHistList where fabricationAllowance less than or equals to UPDATED_FABRICATION_ALLOWANCE
        defaultBuckleArrestorHistShouldBeFound("fabricationAllowance.lessThan=" + UPDATED_FABRICATION_ALLOWANCE);
    }


    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIsBurialIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where isBurial equals to DEFAULT_IS_BURIAL
        defaultBuckleArrestorHistShouldBeFound("isBurial.equals=" + DEFAULT_IS_BURIAL);

        // Get all the buckleArrestorHistList where isBurial equals to UPDATED_IS_BURIAL
        defaultBuckleArrestorHistShouldNotBeFound("isBurial.equals=" + UPDATED_IS_BURIAL);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIsBurialIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where isBurial in DEFAULT_IS_BURIAL or UPDATED_IS_BURIAL
        defaultBuckleArrestorHistShouldBeFound("isBurial.in=" + DEFAULT_IS_BURIAL + "," + UPDATED_IS_BURIAL);

        // Get all the buckleArrestorHistList where isBurial equals to UPDATED_IS_BURIAL
        defaultBuckleArrestorHistShouldNotBeFound("isBurial.in=" + UPDATED_IS_BURIAL);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIsBurialIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where isBurial is not null
        defaultBuckleArrestorHistShouldBeFound("isBurial.specified=true");

        // Get all the buckleArrestorHistList where isBurial is null
        defaultBuckleArrestorHistShouldNotBeFound("isBurial.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIsBurialIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where isBurial greater than or equals to DEFAULT_IS_BURIAL
        defaultBuckleArrestorHistShouldBeFound("isBurial.greaterOrEqualThan=" + DEFAULT_IS_BURIAL);

        // Get all the buckleArrestorHistList where isBurial greater than or equals to UPDATED_IS_BURIAL
        defaultBuckleArrestorHistShouldNotBeFound("isBurial.greaterOrEqualThan=" + UPDATED_IS_BURIAL);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIsBurialIsLessThanSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where isBurial less than or equals to DEFAULT_IS_BURIAL
        defaultBuckleArrestorHistShouldNotBeFound("isBurial.lessThan=" + DEFAULT_IS_BURIAL);

        // Get all the buckleArrestorHistList where isBurial less than or equals to UPDATED_IS_BURIAL
        defaultBuckleArrestorHistShouldBeFound("isBurial.lessThan=" + UPDATED_IS_BURIAL);
    }


    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByBurialDepthIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where burialDepth equals to DEFAULT_BURIAL_DEPTH
        defaultBuckleArrestorHistShouldBeFound("burialDepth.equals=" + DEFAULT_BURIAL_DEPTH);

        // Get all the buckleArrestorHistList where burialDepth equals to UPDATED_BURIAL_DEPTH
        defaultBuckleArrestorHistShouldNotBeFound("burialDepth.equals=" + UPDATED_BURIAL_DEPTH);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByBurialDepthIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where burialDepth in DEFAULT_BURIAL_DEPTH or UPDATED_BURIAL_DEPTH
        defaultBuckleArrestorHistShouldBeFound("burialDepth.in=" + DEFAULT_BURIAL_DEPTH + "," + UPDATED_BURIAL_DEPTH);

        // Get all the buckleArrestorHistList where burialDepth equals to UPDATED_BURIAL_DEPTH
        defaultBuckleArrestorHistShouldNotBeFound("burialDepth.in=" + UPDATED_BURIAL_DEPTH);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByBurialDepthIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where burialDepth is not null
        defaultBuckleArrestorHistShouldBeFound("burialDepth.specified=true");

        // Get all the buckleArrestorHistList where burialDepth is null
        defaultBuckleArrestorHistShouldNotBeFound("burialDepth.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByBurialDepthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where burialDepth greater than or equals to DEFAULT_BURIAL_DEPTH
        defaultBuckleArrestorHistShouldBeFound("burialDepth.greaterOrEqualThan=" + DEFAULT_BURIAL_DEPTH);

        // Get all the buckleArrestorHistList where burialDepth greater than or equals to UPDATED_BURIAL_DEPTH
        defaultBuckleArrestorHistShouldNotBeFound("burialDepth.greaterOrEqualThan=" + UPDATED_BURIAL_DEPTH);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByBurialDepthIsLessThanSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where burialDepth less than or equals to DEFAULT_BURIAL_DEPTH
        defaultBuckleArrestorHistShouldNotBeFound("burialDepth.lessThan=" + DEFAULT_BURIAL_DEPTH);

        // Get all the buckleArrestorHistList where burialDepth less than or equals to UPDATED_BURIAL_DEPTH
        defaultBuckleArrestorHistShouldBeFound("burialDepth.lessThan=" + UPDATED_BURIAL_DEPTH);
    }


    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByFactBurialDepthIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where factBurialDepth equals to DEFAULT_FACT_BURIAL_DEPTH
        defaultBuckleArrestorHistShouldBeFound("factBurialDepth.equals=" + DEFAULT_FACT_BURIAL_DEPTH);

        // Get all the buckleArrestorHistList where factBurialDepth equals to UPDATED_FACT_BURIAL_DEPTH
        defaultBuckleArrestorHistShouldNotBeFound("factBurialDepth.equals=" + UPDATED_FACT_BURIAL_DEPTH);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByFactBurialDepthIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where factBurialDepth in DEFAULT_FACT_BURIAL_DEPTH or UPDATED_FACT_BURIAL_DEPTH
        defaultBuckleArrestorHistShouldBeFound("factBurialDepth.in=" + DEFAULT_FACT_BURIAL_DEPTH + "," + UPDATED_FACT_BURIAL_DEPTH);

        // Get all the buckleArrestorHistList where factBurialDepth equals to UPDATED_FACT_BURIAL_DEPTH
        defaultBuckleArrestorHistShouldNotBeFound("factBurialDepth.in=" + UPDATED_FACT_BURIAL_DEPTH);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByFactBurialDepthIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where factBurialDepth is not null
        defaultBuckleArrestorHistShouldBeFound("factBurialDepth.specified=true");

        // Get all the buckleArrestorHistList where factBurialDepth is null
        defaultBuckleArrestorHistShouldNotBeFound("factBurialDepth.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByFactBurialDepthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where factBurialDepth greater than or equals to DEFAULT_FACT_BURIAL_DEPTH
        defaultBuckleArrestorHistShouldBeFound("factBurialDepth.greaterOrEqualThan=" + DEFAULT_FACT_BURIAL_DEPTH);

        // Get all the buckleArrestorHistList where factBurialDepth greater than or equals to UPDATED_FACT_BURIAL_DEPTH
        defaultBuckleArrestorHistShouldNotBeFound("factBurialDepth.greaterOrEqualThan=" + UPDATED_FACT_BURIAL_DEPTH);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByFactBurialDepthIsLessThanSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where factBurialDepth less than or equals to DEFAULT_FACT_BURIAL_DEPTH
        defaultBuckleArrestorHistShouldNotBeFound("factBurialDepth.lessThan=" + DEFAULT_FACT_BURIAL_DEPTH);

        // Get all the buckleArrestorHistList where factBurialDepth less than or equals to UPDATED_FACT_BURIAL_DEPTH
        defaultBuckleArrestorHistShouldBeFound("factBurialDepth.lessThan=" + UPDATED_FACT_BURIAL_DEPTH);
    }


    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDateManufacturedIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where dateManufactured equals to DEFAULT_DATE_MANUFACTURED
        defaultBuckleArrestorHistShouldBeFound("dateManufactured.equals=" + DEFAULT_DATE_MANUFACTURED);

        // Get all the buckleArrestorHistList where dateManufactured equals to UPDATED_DATE_MANUFACTURED
        defaultBuckleArrestorHistShouldNotBeFound("dateManufactured.equals=" + UPDATED_DATE_MANUFACTURED);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDateManufacturedIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where dateManufactured in DEFAULT_DATE_MANUFACTURED or UPDATED_DATE_MANUFACTURED
        defaultBuckleArrestorHistShouldBeFound("dateManufactured.in=" + DEFAULT_DATE_MANUFACTURED + "," + UPDATED_DATE_MANUFACTURED);

        // Get all the buckleArrestorHistList where dateManufactured equals to UPDATED_DATE_MANUFACTURED
        defaultBuckleArrestorHistShouldNotBeFound("dateManufactured.in=" + UPDATED_DATE_MANUFACTURED);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDateManufacturedIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where dateManufactured is not null
        defaultBuckleArrestorHistShouldBeFound("dateManufactured.specified=true");

        // Get all the buckleArrestorHistList where dateManufactured is null
        defaultBuckleArrestorHistShouldNotBeFound("dateManufactured.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDateManufacturedIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where dateManufactured greater than or equals to DEFAULT_DATE_MANUFACTURED
        defaultBuckleArrestorHistShouldBeFound("dateManufactured.greaterOrEqualThan=" + DEFAULT_DATE_MANUFACTURED);

        // Get all the buckleArrestorHistList where dateManufactured greater than or equals to UPDATED_DATE_MANUFACTURED
        defaultBuckleArrestorHistShouldNotBeFound("dateManufactured.greaterOrEqualThan=" + UPDATED_DATE_MANUFACTURED);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDateManufacturedIsLessThanSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where dateManufactured less than or equals to DEFAULT_DATE_MANUFACTURED
        defaultBuckleArrestorHistShouldNotBeFound("dateManufactured.lessThan=" + DEFAULT_DATE_MANUFACTURED);

        // Get all the buckleArrestorHistList where dateManufactured less than or equals to UPDATED_DATE_MANUFACTURED
        defaultBuckleArrestorHistShouldBeFound("dateManufactured.lessThan=" + UPDATED_DATE_MANUFACTURED);
    }


    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByMillTestPressureIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where millTestPressure equals to DEFAULT_MILL_TEST_PRESSURE
        defaultBuckleArrestorHistShouldBeFound("millTestPressure.equals=" + DEFAULT_MILL_TEST_PRESSURE);

        // Get all the buckleArrestorHistList where millTestPressure equals to UPDATED_MILL_TEST_PRESSURE
        defaultBuckleArrestorHistShouldNotBeFound("millTestPressure.equals=" + UPDATED_MILL_TEST_PRESSURE);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByMillTestPressureIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where millTestPressure in DEFAULT_MILL_TEST_PRESSURE or UPDATED_MILL_TEST_PRESSURE
        defaultBuckleArrestorHistShouldBeFound("millTestPressure.in=" + DEFAULT_MILL_TEST_PRESSURE + "," + UPDATED_MILL_TEST_PRESSURE);

        // Get all the buckleArrestorHistList where millTestPressure equals to UPDATED_MILL_TEST_PRESSURE
        defaultBuckleArrestorHistShouldNotBeFound("millTestPressure.in=" + UPDATED_MILL_TEST_PRESSURE);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByMillTestPressureIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where millTestPressure is not null
        defaultBuckleArrestorHistShouldBeFound("millTestPressure.specified=true");

        // Get all the buckleArrestorHistList where millTestPressure is null
        defaultBuckleArrestorHistShouldNotBeFound("millTestPressure.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDesignPressureIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where designPressure equals to DEFAULT_DESIGN_PRESSURE
        defaultBuckleArrestorHistShouldBeFound("designPressure.equals=" + DEFAULT_DESIGN_PRESSURE);

        // Get all the buckleArrestorHistList where designPressure equals to UPDATED_DESIGN_PRESSURE
        defaultBuckleArrestorHistShouldNotBeFound("designPressure.equals=" + UPDATED_DESIGN_PRESSURE);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDesignPressureIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where designPressure in DEFAULT_DESIGN_PRESSURE or UPDATED_DESIGN_PRESSURE
        defaultBuckleArrestorHistShouldBeFound("designPressure.in=" + DEFAULT_DESIGN_PRESSURE + "," + UPDATED_DESIGN_PRESSURE);

        // Get all the buckleArrestorHistList where designPressure equals to UPDATED_DESIGN_PRESSURE
        defaultBuckleArrestorHistShouldNotBeFound("designPressure.in=" + UPDATED_DESIGN_PRESSURE);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDesignPressureIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where designPressure is not null
        defaultBuckleArrestorHistShouldBeFound("designPressure.specified=true");

        // Get all the buckleArrestorHistList where designPressure is null
        defaultBuckleArrestorHistShouldNotBeFound("designPressure.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIncidentalPressureIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where incidentalPressure equals to DEFAULT_INCIDENTAL_PRESSURE
        defaultBuckleArrestorHistShouldBeFound("incidentalPressure.equals=" + DEFAULT_INCIDENTAL_PRESSURE);

        // Get all the buckleArrestorHistList where incidentalPressure equals to UPDATED_INCIDENTAL_PRESSURE
        defaultBuckleArrestorHistShouldNotBeFound("incidentalPressure.equals=" + UPDATED_INCIDENTAL_PRESSURE);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIncidentalPressureIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where incidentalPressure in DEFAULT_INCIDENTAL_PRESSURE or UPDATED_INCIDENTAL_PRESSURE
        defaultBuckleArrestorHistShouldBeFound("incidentalPressure.in=" + DEFAULT_INCIDENTAL_PRESSURE + "," + UPDATED_INCIDENTAL_PRESSURE);

        // Get all the buckleArrestorHistList where incidentalPressure equals to UPDATED_INCIDENTAL_PRESSURE
        defaultBuckleArrestorHistShouldNotBeFound("incidentalPressure.in=" + UPDATED_INCIDENTAL_PRESSURE);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIncidentalPressureIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where incidentalPressure is not null
        defaultBuckleArrestorHistShouldBeFound("incidentalPressure.specified=true");

        // Get all the buckleArrestorHistList where incidentalPressure is null
        defaultBuckleArrestorHistShouldNotBeFound("incidentalPressure.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDateInstalledIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where dateInstalled equals to DEFAULT_DATE_INSTALLED
        defaultBuckleArrestorHistShouldBeFound("dateInstalled.equals=" + DEFAULT_DATE_INSTALLED);

        // Get all the buckleArrestorHistList where dateInstalled equals to UPDATED_DATE_INSTALLED
        defaultBuckleArrestorHistShouldNotBeFound("dateInstalled.equals=" + UPDATED_DATE_INSTALLED);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDateInstalledIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where dateInstalled in DEFAULT_DATE_INSTALLED or UPDATED_DATE_INSTALLED
        defaultBuckleArrestorHistShouldBeFound("dateInstalled.in=" + DEFAULT_DATE_INSTALLED + "," + UPDATED_DATE_INSTALLED);

        // Get all the buckleArrestorHistList where dateInstalled equals to UPDATED_DATE_INSTALLED
        defaultBuckleArrestorHistShouldNotBeFound("dateInstalled.in=" + UPDATED_DATE_INSTALLED);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDateInstalledIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where dateInstalled is not null
        defaultBuckleArrestorHistShouldBeFound("dateInstalled.specified=true");

        // Get all the buckleArrestorHistList where dateInstalled is null
        defaultBuckleArrestorHistShouldNotBeFound("dateInstalled.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDateInstalledIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where dateInstalled greater than or equals to DEFAULT_DATE_INSTALLED
        defaultBuckleArrestorHistShouldBeFound("dateInstalled.greaterOrEqualThan=" + DEFAULT_DATE_INSTALLED);

        // Get all the buckleArrestorHistList where dateInstalled greater than or equals to UPDATED_DATE_INSTALLED
        defaultBuckleArrestorHistShouldNotBeFound("dateInstalled.greaterOrEqualThan=" + UPDATED_DATE_INSTALLED);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDateInstalledIsLessThanSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where dateInstalled less than or equals to DEFAULT_DATE_INSTALLED
        defaultBuckleArrestorHistShouldNotBeFound("dateInstalled.lessThan=" + DEFAULT_DATE_INSTALLED);

        // Get all the buckleArrestorHistList where dateInstalled less than or equals to UPDATED_DATE_INSTALLED
        defaultBuckleArrestorHistShouldBeFound("dateInstalled.lessThan=" + UPDATED_DATE_INSTALLED);
    }


    @Test
    @Transactional
    public void getAllBuckleArrestorHistsBySeamOrientIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where seamOrient equals to DEFAULT_SEAM_ORIENT
        defaultBuckleArrestorHistShouldBeFound("seamOrient.equals=" + DEFAULT_SEAM_ORIENT);

        // Get all the buckleArrestorHistList where seamOrient equals to UPDATED_SEAM_ORIENT
        defaultBuckleArrestorHistShouldNotBeFound("seamOrient.equals=" + UPDATED_SEAM_ORIENT);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsBySeamOrientIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where seamOrient in DEFAULT_SEAM_ORIENT or UPDATED_SEAM_ORIENT
        defaultBuckleArrestorHistShouldBeFound("seamOrient.in=" + DEFAULT_SEAM_ORIENT + "," + UPDATED_SEAM_ORIENT);

        // Get all the buckleArrestorHistList where seamOrient equals to UPDATED_SEAM_ORIENT
        defaultBuckleArrestorHistShouldNotBeFound("seamOrient.in=" + UPDATED_SEAM_ORIENT);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsBySeamOrientIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where seamOrient is not null
        defaultBuckleArrestorHistShouldBeFound("seamOrient.specified=true");

        // Get all the buckleArrestorHistList where seamOrient is null
        defaultBuckleArrestorHistShouldNotBeFound("seamOrient.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsBySeamAngleIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where seamAngle equals to DEFAULT_SEAM_ANGLE
        defaultBuckleArrestorHistShouldBeFound("seamAngle.equals=" + DEFAULT_SEAM_ANGLE);

        // Get all the buckleArrestorHistList where seamAngle equals to UPDATED_SEAM_ANGLE
        defaultBuckleArrestorHistShouldNotBeFound("seamAngle.equals=" + UPDATED_SEAM_ANGLE);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsBySeamAngleIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where seamAngle in DEFAULT_SEAM_ANGLE or UPDATED_SEAM_ANGLE
        defaultBuckleArrestorHistShouldBeFound("seamAngle.in=" + DEFAULT_SEAM_ANGLE + "," + UPDATED_SEAM_ANGLE);

        // Get all the buckleArrestorHistList where seamAngle equals to UPDATED_SEAM_ANGLE
        defaultBuckleArrestorHistShouldNotBeFound("seamAngle.in=" + UPDATED_SEAM_ANGLE);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsBySeamAngleIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where seamAngle is not null
        defaultBuckleArrestorHistShouldBeFound("seamAngle.specified=true");

        // Get all the buckleArrestorHistList where seamAngle is null
        defaultBuckleArrestorHistShouldNotBeFound("seamAngle.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByAzimuthIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where azimuth equals to DEFAULT_AZIMUTH
        defaultBuckleArrestorHistShouldBeFound("azimuth.equals=" + DEFAULT_AZIMUTH);

        // Get all the buckleArrestorHistList where azimuth equals to UPDATED_AZIMUTH
        defaultBuckleArrestorHistShouldNotBeFound("azimuth.equals=" + UPDATED_AZIMUTH);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByAzimuthIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where azimuth in DEFAULT_AZIMUTH or UPDATED_AZIMUTH
        defaultBuckleArrestorHistShouldBeFound("azimuth.in=" + DEFAULT_AZIMUTH + "," + UPDATED_AZIMUTH);

        // Get all the buckleArrestorHistList where azimuth equals to UPDATED_AZIMUTH
        defaultBuckleArrestorHistShouldNotBeFound("azimuth.in=" + UPDATED_AZIMUTH);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByAzimuthIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where azimuth is not null
        defaultBuckleArrestorHistShouldBeFound("azimuth.specified=true");

        // Get all the buckleArrestorHistList where azimuth is null
        defaultBuckleArrestorHistShouldNotBeFound("azimuth.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsBySeabInstallTempIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where seabInstallTemp equals to DEFAULT_SEAB_INSTALL_TEMP
        defaultBuckleArrestorHistShouldBeFound("seabInstallTemp.equals=" + DEFAULT_SEAB_INSTALL_TEMP);

        // Get all the buckleArrestorHistList where seabInstallTemp equals to UPDATED_SEAB_INSTALL_TEMP
        defaultBuckleArrestorHistShouldNotBeFound("seabInstallTemp.equals=" + UPDATED_SEAB_INSTALL_TEMP);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsBySeabInstallTempIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where seabInstallTemp in DEFAULT_SEAB_INSTALL_TEMP or UPDATED_SEAB_INSTALL_TEMP
        defaultBuckleArrestorHistShouldBeFound("seabInstallTemp.in=" + DEFAULT_SEAB_INSTALL_TEMP + "," + UPDATED_SEAB_INSTALL_TEMP);

        // Get all the buckleArrestorHistList where seabInstallTemp equals to UPDATED_SEAB_INSTALL_TEMP
        defaultBuckleArrestorHistShouldNotBeFound("seabInstallTemp.in=" + UPDATED_SEAB_INSTALL_TEMP);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsBySeabInstallTempIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where seabInstallTemp is not null
        defaultBuckleArrestorHistShouldBeFound("seabInstallTemp.specified=true");

        // Get all the buckleArrestorHistList where seabInstallTemp is null
        defaultBuckleArrestorHistShouldNotBeFound("seabInstallTemp.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByVerticalAngleIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where verticalAngle equals to DEFAULT_VERTICAL_ANGLE
        defaultBuckleArrestorHistShouldBeFound("verticalAngle.equals=" + DEFAULT_VERTICAL_ANGLE);

        // Get all the buckleArrestorHistList where verticalAngle equals to UPDATED_VERTICAL_ANGLE
        defaultBuckleArrestorHistShouldNotBeFound("verticalAngle.equals=" + UPDATED_VERTICAL_ANGLE);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByVerticalAngleIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where verticalAngle in DEFAULT_VERTICAL_ANGLE or UPDATED_VERTICAL_ANGLE
        defaultBuckleArrestorHistShouldBeFound("verticalAngle.in=" + DEFAULT_VERTICAL_ANGLE + "," + UPDATED_VERTICAL_ANGLE);

        // Get all the buckleArrestorHistList where verticalAngle equals to UPDATED_VERTICAL_ANGLE
        defaultBuckleArrestorHistShouldNotBeFound("verticalAngle.in=" + UPDATED_VERTICAL_ANGLE);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByVerticalAngleIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where verticalAngle is not null
        defaultBuckleArrestorHistShouldBeFound("verticalAngle.specified=true");

        // Get all the buckleArrestorHistList where verticalAngle is null
        defaultBuckleArrestorHistShouldNotBeFound("verticalAngle.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByHeatTreatDuratIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where heatTreatDurat equals to DEFAULT_HEAT_TREAT_DURAT
        defaultBuckleArrestorHistShouldBeFound("heatTreatDurat.equals=" + DEFAULT_HEAT_TREAT_DURAT);

        // Get all the buckleArrestorHistList where heatTreatDurat equals to UPDATED_HEAT_TREAT_DURAT
        defaultBuckleArrestorHistShouldNotBeFound("heatTreatDurat.equals=" + UPDATED_HEAT_TREAT_DURAT);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByHeatTreatDuratIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where heatTreatDurat in DEFAULT_HEAT_TREAT_DURAT or UPDATED_HEAT_TREAT_DURAT
        defaultBuckleArrestorHistShouldBeFound("heatTreatDurat.in=" + DEFAULT_HEAT_TREAT_DURAT + "," + UPDATED_HEAT_TREAT_DURAT);

        // Get all the buckleArrestorHistList where heatTreatDurat equals to UPDATED_HEAT_TREAT_DURAT
        defaultBuckleArrestorHistShouldNotBeFound("heatTreatDurat.in=" + UPDATED_HEAT_TREAT_DURAT);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByHeatTreatDuratIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where heatTreatDurat is not null
        defaultBuckleArrestorHistShouldBeFound("heatTreatDurat.specified=true");

        // Get all the buckleArrestorHistList where heatTreatDurat is null
        defaultBuckleArrestorHistShouldNotBeFound("heatTreatDurat.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByMaxDesignTempIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where maxDesignTemp equals to DEFAULT_MAX_DESIGN_TEMP
        defaultBuckleArrestorHistShouldBeFound("maxDesignTemp.equals=" + DEFAULT_MAX_DESIGN_TEMP);

        // Get all the buckleArrestorHistList where maxDesignTemp equals to UPDATED_MAX_DESIGN_TEMP
        defaultBuckleArrestorHistShouldNotBeFound("maxDesignTemp.equals=" + UPDATED_MAX_DESIGN_TEMP);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByMaxDesignTempIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where maxDesignTemp in DEFAULT_MAX_DESIGN_TEMP or UPDATED_MAX_DESIGN_TEMP
        defaultBuckleArrestorHistShouldBeFound("maxDesignTemp.in=" + DEFAULT_MAX_DESIGN_TEMP + "," + UPDATED_MAX_DESIGN_TEMP);

        // Get all the buckleArrestorHistList where maxDesignTemp equals to UPDATED_MAX_DESIGN_TEMP
        defaultBuckleArrestorHistShouldNotBeFound("maxDesignTemp.in=" + UPDATED_MAX_DESIGN_TEMP);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByMaxDesignTempIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where maxDesignTemp is not null
        defaultBuckleArrestorHistShouldBeFound("maxDesignTemp.specified=true");

        // Get all the buckleArrestorHistList where maxDesignTemp is null
        defaultBuckleArrestorHistShouldNotBeFound("maxDesignTemp.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByHeatNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where heatNumber equals to DEFAULT_HEAT_NUMBER
        defaultBuckleArrestorHistShouldBeFound("heatNumber.equals=" + DEFAULT_HEAT_NUMBER);

        // Get all the buckleArrestorHistList where heatNumber equals to UPDATED_HEAT_NUMBER
        defaultBuckleArrestorHistShouldNotBeFound("heatNumber.equals=" + UPDATED_HEAT_NUMBER);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByHeatNumberIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where heatNumber in DEFAULT_HEAT_NUMBER or UPDATED_HEAT_NUMBER
        defaultBuckleArrestorHistShouldBeFound("heatNumber.in=" + DEFAULT_HEAT_NUMBER + "," + UPDATED_HEAT_NUMBER);

        // Get all the buckleArrestorHistList where heatNumber equals to UPDATED_HEAT_NUMBER
        defaultBuckleArrestorHistShouldNotBeFound("heatNumber.in=" + UPDATED_HEAT_NUMBER);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByHeatNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where heatNumber is not null
        defaultBuckleArrestorHistShouldBeFound("heatNumber.specified=true");

        // Get all the buckleArrestorHistList where heatNumber is null
        defaultBuckleArrestorHistShouldNotBeFound("heatNumber.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByCoordIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where coord equals to DEFAULT_COORD
        defaultBuckleArrestorHistShouldBeFound("coord.equals=" + DEFAULT_COORD);

        // Get all the buckleArrestorHistList where coord equals to UPDATED_COORD
        defaultBuckleArrestorHistShouldNotBeFound("coord.equals=" + UPDATED_COORD);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByCoordIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where coord in DEFAULT_COORD or UPDATED_COORD
        defaultBuckleArrestorHistShouldBeFound("coord.in=" + DEFAULT_COORD + "," + UPDATED_COORD);

        // Get all the buckleArrestorHistList where coord equals to UPDATED_COORD
        defaultBuckleArrestorHistShouldNotBeFound("coord.in=" + UPDATED_COORD);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByCoordIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where coord is not null
        defaultBuckleArrestorHistShouldBeFound("coord.specified=true");

        // Get all the buckleArrestorHistList where coord is null
        defaultBuckleArrestorHistShouldNotBeFound("coord.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDesignCoordIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where designCoord equals to DEFAULT_DESIGN_COORD
        defaultBuckleArrestorHistShouldBeFound("designCoord.equals=" + DEFAULT_DESIGN_COORD);

        // Get all the buckleArrestorHistList where designCoord equals to UPDATED_DESIGN_COORD
        defaultBuckleArrestorHistShouldNotBeFound("designCoord.equals=" + UPDATED_DESIGN_COORD);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDesignCoordIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where designCoord in DEFAULT_DESIGN_COORD or UPDATED_DESIGN_COORD
        defaultBuckleArrestorHistShouldBeFound("designCoord.in=" + DEFAULT_DESIGN_COORD + "," + UPDATED_DESIGN_COORD);

        // Get all the buckleArrestorHistList where designCoord equals to UPDATED_DESIGN_COORD
        defaultBuckleArrestorHistShouldNotBeFound("designCoord.in=" + UPDATED_DESIGN_COORD);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDesignCoordIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where designCoord is not null
        defaultBuckleArrestorHistShouldBeFound("designCoord.specified=true");

        // Get all the buckleArrestorHistList where designCoord is null
        defaultBuckleArrestorHistShouldNotBeFound("designCoord.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByKpStartIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where kpStart equals to DEFAULT_KP_START
        defaultBuckleArrestorHistShouldBeFound("kpStart.equals=" + DEFAULT_KP_START);

        // Get all the buckleArrestorHistList where kpStart equals to UPDATED_KP_START
        defaultBuckleArrestorHistShouldNotBeFound("kpStart.equals=" + UPDATED_KP_START);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByKpStartIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where kpStart in DEFAULT_KP_START or UPDATED_KP_START
        defaultBuckleArrestorHistShouldBeFound("kpStart.in=" + DEFAULT_KP_START + "," + UPDATED_KP_START);

        // Get all the buckleArrestorHistList where kpStart equals to UPDATED_KP_START
        defaultBuckleArrestorHistShouldNotBeFound("kpStart.in=" + UPDATED_KP_START);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByKpStartIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where kpStart is not null
        defaultBuckleArrestorHistShouldBeFound("kpStart.specified=true");

        // Get all the buckleArrestorHistList where kpStart is null
        defaultBuckleArrestorHistShouldNotBeFound("kpStart.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByKpEndIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where kpEnd equals to DEFAULT_KP_END
        defaultBuckleArrestorHistShouldBeFound("kpEnd.equals=" + DEFAULT_KP_END);

        // Get all the buckleArrestorHistList where kpEnd equals to UPDATED_KP_END
        defaultBuckleArrestorHistShouldNotBeFound("kpEnd.equals=" + UPDATED_KP_END);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByKpEndIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where kpEnd in DEFAULT_KP_END or UPDATED_KP_END
        defaultBuckleArrestorHistShouldBeFound("kpEnd.in=" + DEFAULT_KP_END + "," + UPDATED_KP_END);

        // Get all the buckleArrestorHistList where kpEnd equals to UPDATED_KP_END
        defaultBuckleArrestorHistShouldNotBeFound("kpEnd.in=" + UPDATED_KP_END);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByKpEndIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where kpEnd is not null
        defaultBuckleArrestorHistShouldBeFound("kpEnd.specified=true");

        // Get all the buckleArrestorHistList where kpEnd is null
        defaultBuckleArrestorHistShouldNotBeFound("kpEnd.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultBuckleArrestorHistShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the buckleArrestorHistList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultBuckleArrestorHistShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultBuckleArrestorHistShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the buckleArrestorHistList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultBuckleArrestorHistShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where isCurrentFlag is not null
        defaultBuckleArrestorHistShouldBeFound("isCurrentFlag.specified=true");

        // Get all the buckleArrestorHistList where isCurrentFlag is null
        defaultBuckleArrestorHistShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultBuckleArrestorHistShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the buckleArrestorHistList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultBuckleArrestorHistShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultBuckleArrestorHistShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the buckleArrestorHistList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultBuckleArrestorHistShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIdStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where idStatus equals to DEFAULT_ID_STATUS
        defaultBuckleArrestorHistShouldBeFound("idStatus.equals=" + DEFAULT_ID_STATUS);

        // Get all the buckleArrestorHistList where idStatus equals to UPDATED_ID_STATUS
        defaultBuckleArrestorHistShouldNotBeFound("idStatus.equals=" + UPDATED_ID_STATUS);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIdStatusIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where idStatus in DEFAULT_ID_STATUS or UPDATED_ID_STATUS
        defaultBuckleArrestorHistShouldBeFound("idStatus.in=" + DEFAULT_ID_STATUS + "," + UPDATED_ID_STATUS);

        // Get all the buckleArrestorHistList where idStatus equals to UPDATED_ID_STATUS
        defaultBuckleArrestorHistShouldNotBeFound("idStatus.in=" + UPDATED_ID_STATUS);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIdStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where idStatus is not null
        defaultBuckleArrestorHistShouldBeFound("idStatus.specified=true");

        // Get all the buckleArrestorHistList where idStatus is null
        defaultBuckleArrestorHistShouldNotBeFound("idStatus.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIdStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where idStatus greater than or equals to DEFAULT_ID_STATUS
        defaultBuckleArrestorHistShouldBeFound("idStatus.greaterOrEqualThan=" + DEFAULT_ID_STATUS);

        // Get all the buckleArrestorHistList where idStatus greater than or equals to UPDATED_ID_STATUS
        defaultBuckleArrestorHistShouldNotBeFound("idStatus.greaterOrEqualThan=" + UPDATED_ID_STATUS);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIdStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where idStatus less than or equals to DEFAULT_ID_STATUS
        defaultBuckleArrestorHistShouldNotBeFound("idStatus.lessThan=" + DEFAULT_ID_STATUS);

        // Get all the buckleArrestorHistList where idStatus less than or equals to UPDATED_ID_STATUS
        defaultBuckleArrestorHistShouldBeFound("idStatus.lessThan=" + UPDATED_ID_STATUS);
    }


    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where description equals to DEFAULT_DESCRIPTION
        defaultBuckleArrestorHistShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the buckleArrestorHistList where description equals to UPDATED_DESCRIPTION
        defaultBuckleArrestorHistShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultBuckleArrestorHistShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the buckleArrestorHistList where description equals to UPDATED_DESCRIPTION
        defaultBuckleArrestorHistShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where description is not null
        defaultBuckleArrestorHistShouldBeFound("description.specified=true");

        // Get all the buckleArrestorHistList where description is null
        defaultBuckleArrestorHistShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByCommentIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where comment equals to DEFAULT_COMMENT
        defaultBuckleArrestorHistShouldBeFound("comment.equals=" + DEFAULT_COMMENT);

        // Get all the buckleArrestorHistList where comment equals to UPDATED_COMMENT
        defaultBuckleArrestorHistShouldNotBeFound("comment.equals=" + UPDATED_COMMENT);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByCommentIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where comment in DEFAULT_COMMENT or UPDATED_COMMENT
        defaultBuckleArrestorHistShouldBeFound("comment.in=" + DEFAULT_COMMENT + "," + UPDATED_COMMENT);

        // Get all the buckleArrestorHistList where comment equals to UPDATED_COMMENT
        defaultBuckleArrestorHistShouldNotBeFound("comment.in=" + UPDATED_COMMENT);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByCommentIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where comment is not null
        defaultBuckleArrestorHistShouldBeFound("comment.specified=true");

        // Get all the buckleArrestorHistList where comment is null
        defaultBuckleArrestorHistShouldNotBeFound("comment.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultBuckleArrestorHistShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the buckleArrestorHistList where dateCreate equals to UPDATED_DATE_CREATE
        defaultBuckleArrestorHistShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultBuckleArrestorHistShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the buckleArrestorHistList where dateCreate equals to UPDATED_DATE_CREATE
        defaultBuckleArrestorHistShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where dateCreate is not null
        defaultBuckleArrestorHistShouldBeFound("dateCreate.specified=true");

        // Get all the buckleArrestorHistList where dateCreate is null
        defaultBuckleArrestorHistShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultBuckleArrestorHistShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the buckleArrestorHistList where dateEdit equals to UPDATED_DATE_EDIT
        defaultBuckleArrestorHistShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultBuckleArrestorHistShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the buckleArrestorHistList where dateEdit equals to UPDATED_DATE_EDIT
        defaultBuckleArrestorHistShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where dateEdit is not null
        defaultBuckleArrestorHistShouldBeFound("dateEdit.specified=true");

        // Get all the buckleArrestorHistList where dateEdit is null
        defaultBuckleArrestorHistShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where creator equals to DEFAULT_CREATOR
        defaultBuckleArrestorHistShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the buckleArrestorHistList where creator equals to UPDATED_CREATOR
        defaultBuckleArrestorHistShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultBuckleArrestorHistShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the buckleArrestorHistList where creator equals to UPDATED_CREATOR
        defaultBuckleArrestorHistShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where creator is not null
        defaultBuckleArrestorHistShouldBeFound("creator.specified=true");

        // Get all the buckleArrestorHistList where creator is null
        defaultBuckleArrestorHistShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where editor equals to DEFAULT_EDITOR
        defaultBuckleArrestorHistShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the buckleArrestorHistList where editor equals to UPDATED_EDITOR
        defaultBuckleArrestorHistShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultBuckleArrestorHistShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the buckleArrestorHistList where editor equals to UPDATED_EDITOR
        defaultBuckleArrestorHistShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        // Get all the buckleArrestorHistList where editor is not null
        defaultBuckleArrestorHistShouldBeFound("editor.specified=true");

        // Get all the buckleArrestorHistList where editor is null
        defaultBuckleArrestorHistShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        BuckleArrestor id = buckleArrestorHist.getId();
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);
        Long idId = id.getId();

        // Get all the buckleArrestorHistList where id equals to idId
        defaultBuckleArrestorHistShouldBeFound("idId.equals=" + idId);

        // Get all the buckleArrestorHistList where id equals to idId + 1
        defaultBuckleArrestorHistShouldNotBeFound("idId.equals=" + (idId + 1));
    }


    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIdPipelineSectionIsEqualToSomething() throws Exception {
        // Get already existing entity
        PipelineSection idPipelineSection = buckleArrestorHist.getIdPipelineSection();
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);
        Long idPipelineSectionId = idPipelineSection.getId();

        // Get all the buckleArrestorHistList where idPipelineSection equals to idPipelineSectionId
        defaultBuckleArrestorHistShouldBeFound("idPipelineSectionId.equals=" + idPipelineSectionId);

        // Get all the buckleArrestorHistList where idPipelineSection equals to idPipelineSectionId + 1
        defaultBuckleArrestorHistShouldNotBeFound("idPipelineSectionId.equals=" + (idPipelineSectionId + 1));
    }


    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIdTypeIsEqualToSomething() throws Exception {
        // Get already existing entity
        ListBucklarrType idType = buckleArrestorHist.getIdType();
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);
        Long idTypeId = idType.getId();

        // Get all the buckleArrestorHistList where idType equals to idTypeId
        defaultBuckleArrestorHistShouldBeFound("idTypeId.equals=" + idTypeId);

        // Get all the buckleArrestorHistList where idType equals to idTypeId + 1
        defaultBuckleArrestorHistShouldNotBeFound("idTypeId.equals=" + (idTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIdInternalCoatTypeIsEqualToSomething() throws Exception {
        // Get already existing entity
        ListInternalCoating idInternalCoatType = buckleArrestorHist.getIdInternalCoatType();
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);
        Long idInternalCoatTypeId = idInternalCoatType.getId();

        // Get all the buckleArrestorHistList where idInternalCoatType equals to idInternalCoatTypeId
        defaultBuckleArrestorHistShouldBeFound("idInternalCoatTypeId.equals=" + idInternalCoatTypeId);

        // Get all the buckleArrestorHistList where idInternalCoatType equals to idInternalCoatTypeId + 1
        defaultBuckleArrestorHistShouldNotBeFound("idInternalCoatTypeId.equals=" + (idInternalCoatTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIdExternalCoatTypeIsEqualToSomething() throws Exception {
        // Get already existing entity
        ListExternalCoating idExternalCoatType = buckleArrestorHist.getIdExternalCoatType();
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);
        Long idExternalCoatTypeId = idExternalCoatType.getId();

        // Get all the buckleArrestorHistList where idExternalCoatType equals to idExternalCoatTypeId
        defaultBuckleArrestorHistShouldBeFound("idExternalCoatTypeId.equals=" + idExternalCoatTypeId);

        // Get all the buckleArrestorHistList where idExternalCoatType equals to idExternalCoatTypeId + 1
        defaultBuckleArrestorHistShouldNotBeFound("idExternalCoatTypeId.equals=" + (idExternalCoatTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIdNominalWallThicknessIsEqualToSomething() throws Exception {
        // Initialize the database
        ListNominalWallThickness idNominalWallThickness = ListNominalWallThicknessResourceIT.createEntity(em);
        em.persist(idNominalWallThickness);
        em.flush();
        buckleArrestorHist.setIdNominalWallThickness(idNominalWallThickness);
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);
        Long idNominalWallThicknessId = idNominalWallThickness.getId();

        // Get all the buckleArrestorHistList where idNominalWallThickness equals to idNominalWallThicknessId
        defaultBuckleArrestorHistShouldBeFound("idNominalWallThicknessId.equals=" + idNominalWallThicknessId);

        // Get all the buckleArrestorHistList where idNominalWallThickness equals to idNominalWallThicknessId + 1
        defaultBuckleArrestorHistShouldNotBeFound("idNominalWallThicknessId.equals=" + (idNominalWallThicknessId + 1));
    }


    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIdPipeJointIsEqualToSomething() throws Exception {
        // Initialize the database
        Pipejoint idPipeJoint = PipejointResourceIT.createEntity(em);
        em.persist(idPipeJoint);
        em.flush();
        buckleArrestorHist.setIdPipeJoint(idPipeJoint);
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);
        Long idPipeJointId = idPipeJoint.getId();

        // Get all the buckleArrestorHistList where idPipeJoint equals to idPipeJointId
        defaultBuckleArrestorHistShouldBeFound("idPipeJointId.equals=" + idPipeJointId);

        // Get all the buckleArrestorHistList where idPipeJoint equals to idPipeJointId + 1
        defaultBuckleArrestorHistShouldNotBeFound("idPipeJointId.equals=" + (idPipeJointId + 1));
    }


    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIdManufacturerIsEqualToSomething() throws Exception {
        // Initialize the database
        ListBucklarrManufacturer idManufacturer = ListBucklarrManufacturerResourceIT.createEntity(em);
        em.persist(idManufacturer);
        em.flush();
        buckleArrestorHist.setIdManufacturer(idManufacturer);
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);
        Long idManufacturerId = idManufacturer.getId();

        // Get all the buckleArrestorHistList where idManufacturer equals to idManufacturerId
        defaultBuckleArrestorHistShouldBeFound("idManufacturerId.equals=" + idManufacturerId);

        // Get all the buckleArrestorHistList where idManufacturer equals to idManufacturerId + 1
        defaultBuckleArrestorHistShouldNotBeFound("idManufacturerId.equals=" + (idManufacturerId + 1));
    }


    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIdSpecificationIsEqualToSomething() throws Exception {
        // Initialize the database
        ListBucklarrSpecification idSpecification = ListBucklarrSpecificationResourceIT.createEntity(em);
        em.persist(idSpecification);
        em.flush();
        buckleArrestorHist.setIdSpecification(idSpecification);
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);
        Long idSpecificationId = idSpecification.getId();

        // Get all the buckleArrestorHistList where idSpecification equals to idSpecificationId
        defaultBuckleArrestorHistShouldBeFound("idSpecificationId.equals=" + idSpecificationId);

        // Get all the buckleArrestorHistList where idSpecification equals to idSpecificationId + 1
        defaultBuckleArrestorHistShouldNotBeFound("idSpecificationId.equals=" + (idSpecificationId + 1));
    }


    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIdLongSeamWeldTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        ListLongSeamWeldType idLongSeamWeldType = ListLongSeamWeldTypeResourceIT.createEntity(em);
        em.persist(idLongSeamWeldType);
        em.flush();
        buckleArrestorHist.setIdLongSeamWeldType(idLongSeamWeldType);
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);
        Long idLongSeamWeldTypeId = idLongSeamWeldType.getId();

        // Get all the buckleArrestorHistList where idLongSeamWeldType equals to idLongSeamWeldTypeId
        defaultBuckleArrestorHistShouldBeFound("idLongSeamWeldTypeId.equals=" + idLongSeamWeldTypeId);

        // Get all the buckleArrestorHistList where idLongSeamWeldType equals to idLongSeamWeldTypeId + 1
        defaultBuckleArrestorHistShouldNotBeFound("idLongSeamWeldTypeId.equals=" + (idLongSeamWeldTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIdFabricationTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        ListFabricationType idFabricationType = ListFabricationTypeResourceIT.createEntity(em);
        em.persist(idFabricationType);
        em.flush();
        buckleArrestorHist.setIdFabricationType(idFabricationType);
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);
        Long idFabricationTypeId = idFabricationType.getId();

        // Get all the buckleArrestorHistList where idFabricationType equals to idFabricationTypeId
        defaultBuckleArrestorHistShouldBeFound("idFabricationTypeId.equals=" + idFabricationTypeId);

        // Get all the buckleArrestorHistList where idFabricationType equals to idFabricationTypeId + 1
        defaultBuckleArrestorHistShouldNotBeFound("idFabricationTypeId.equals=" + (idFabricationTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIdMaterialIsEqualToSomething() throws Exception {
        // Initialize the database
        ListMaterial idMaterial = ListMaterialResourceIT.createEntity(em);
        em.persist(idMaterial);
        em.flush();
        buckleArrestorHist.setIdMaterial(idMaterial);
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);
        Long idMaterialId = idMaterial.getId();

        // Get all the buckleArrestorHistList where idMaterial equals to idMaterialId
        defaultBuckleArrestorHistShouldBeFound("idMaterialId.equals=" + idMaterialId);

        // Get all the buckleArrestorHistList where idMaterial equals to idMaterialId + 1
        defaultBuckleArrestorHistShouldNotBeFound("idMaterialId.equals=" + (idMaterialId + 1));
    }


    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIdMillLocationIsEqualToSomething() throws Exception {
        // Initialize the database
        ListMillLocation idMillLocation = ListMillLocationResourceIT.createEntity(em);
        em.persist(idMillLocation);
        em.flush();
        buckleArrestorHist.setIdMillLocation(idMillLocation);
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);
        Long idMillLocationId = idMillLocation.getId();

        // Get all the buckleArrestorHistList where idMillLocation equals to idMillLocationId
        defaultBuckleArrestorHistShouldBeFound("idMillLocationId.equals=" + idMillLocationId);

        // Get all the buckleArrestorHistList where idMillLocation equals to idMillLocationId + 1
        defaultBuckleArrestorHistShouldNotBeFound("idMillLocationId.equals=" + (idMillLocationId + 1));
    }


    @Test
    @Transactional
    public void getAllBuckleArrestorHistsByIdSteelGradeIsEqualToSomething() throws Exception {
        // Initialize the database
        ListSteelGrade idSteelGrade = ListSteelGradeResourceIT.createEntity(em);
        em.persist(idSteelGrade);
        em.flush();
        buckleArrestorHist.setIdSteelGrade(idSteelGrade);
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);
        Long idSteelGradeId = idSteelGrade.getId();

        // Get all the buckleArrestorHistList where idSteelGrade equals to idSteelGradeId
        defaultBuckleArrestorHistShouldBeFound("idSteelGradeId.equals=" + idSteelGradeId);

        // Get all the buckleArrestorHistList where idSteelGrade equals to idSteelGradeId + 1
        defaultBuckleArrestorHistShouldNotBeFound("idSteelGradeId.equals=" + (idSteelGradeId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultBuckleArrestorHistShouldBeFound(String filter) throws Exception {
        restBuckleArrestorHistMockMvc.perform(get("/api/buckle-arrestor-hists?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(buckleArrestorHist.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateFrom").value(hasItem(DEFAULT_DATE_FROM.toString())))
            .andExpect(jsonPath("$.[*].dateTo").value(hasItem(DEFAULT_DATE_TO.toString())))
            .andExpect(jsonPath("$.[*].idWrk").value(hasItem(DEFAULT_ID_WRK.intValue())))
            .andExpect(jsonPath("$.[*].serialNum").value(hasItem(DEFAULT_SERIAL_NUM)))
            .andExpect(jsonPath("$.[*].diameterOuterSteel").value(hasItem(DEFAULT_DIAMETER_OUTER_STEEL)))
            .andExpect(jsonPath("$.[*].internalCoatThickness").value(hasItem(DEFAULT_INTERNAL_COAT_THICKNESS.intValue())))
            .andExpect(jsonPath("$.[*].externalCoatThickness").value(hasItem(DEFAULT_EXTERNAL_COAT_THICKNESS.intValue())))
            .andExpect(jsonPath("$.[*].isConcrCoating").value(hasItem(DEFAULT_IS_CONCR_COATING)))
            .andExpect(jsonPath("$.[*].concrCoatThickness").value(hasItem(DEFAULT_CONCR_COAT_THICKNESS.intValue())))
            .andExpect(jsonPath("$.[*].concrCoatDensity").value(hasItem(DEFAULT_CONCR_COAT_DENSITY.intValue())))
            .andExpect(jsonPath("$.[*].measColWallThickness").value(hasItem(DEFAULT_MEAS_COL_WALL_THICKNESS.intValue())))
            .andExpect(jsonPath("$.[*].measPipeWallThickness").value(hasItem(DEFAULT_MEAS_PIPE_WALL_THICKNESS.intValue())))
            .andExpect(jsonPath("$.[*].colLength").value(hasItem(DEFAULT_COL_LENGTH)))
            .andExpect(jsonPath("$.[*].pipeLength").value(hasItem(DEFAULT_PIPE_LENGTH)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].smts").value(hasItem(DEFAULT_SMTS.intValue())))
            .andExpect(jsonPath("$.[*].smys").value(hasItem(DEFAULT_SMYS.intValue())))
            .andExpect(jsonPath("$.[*].sdbm").value(hasItem(DEFAULT_SDBM.intValue())))
            .andExpect(jsonPath("$.[*].sdaf").value(hasItem(DEFAULT_SDAF.intValue())))
            .andExpect(jsonPath("$.[*].sdcs").value(hasItem(DEFAULT_SDCS.intValue())))
            .andExpect(jsonPath("$.[*].allowTensStrain").value(hasItem(DEFAULT_ALLOW_TENS_STRAIN.intValue())))
            .andExpect(jsonPath("$.[*].corrosionAllowance").value(hasItem(DEFAULT_CORROSION_ALLOWANCE)))
            .andExpect(jsonPath("$.[*].fabricationAllowance").value(hasItem(DEFAULT_FABRICATION_ALLOWANCE)))
            .andExpect(jsonPath("$.[*].isBurial").value(hasItem(DEFAULT_IS_BURIAL)))
            .andExpect(jsonPath("$.[*].burialDepth").value(hasItem(DEFAULT_BURIAL_DEPTH)))
            .andExpect(jsonPath("$.[*].factBurialDepth").value(hasItem(DEFAULT_FACT_BURIAL_DEPTH)))
            .andExpect(jsonPath("$.[*].dateManufactured").value(hasItem(DEFAULT_DATE_MANUFACTURED.toString())))
            .andExpect(jsonPath("$.[*].millTestPressure").value(hasItem(DEFAULT_MILL_TEST_PRESSURE.intValue())))
            .andExpect(jsonPath("$.[*].designPressure").value(hasItem(DEFAULT_DESIGN_PRESSURE.intValue())))
            .andExpect(jsonPath("$.[*].incidentalPressure").value(hasItem(DEFAULT_INCIDENTAL_PRESSURE.intValue())))
            .andExpect(jsonPath("$.[*].dateInstalled").value(hasItem(DEFAULT_DATE_INSTALLED.toString())))
            .andExpect(jsonPath("$.[*].seamOrient").value(hasItem(DEFAULT_SEAM_ORIENT.intValue())))
            .andExpect(jsonPath("$.[*].seamAngle").value(hasItem(DEFAULT_SEAM_ANGLE.intValue())))
            .andExpect(jsonPath("$.[*].azimuth").value(hasItem(DEFAULT_AZIMUTH.intValue())))
            .andExpect(jsonPath("$.[*].seabInstallTemp").value(hasItem(DEFAULT_SEAB_INSTALL_TEMP.intValue())))
            .andExpect(jsonPath("$.[*].verticalAngle").value(hasItem(DEFAULT_VERTICAL_ANGLE.intValue())))
            .andExpect(jsonPath("$.[*].heatTreatDurat").value(hasItem(DEFAULT_HEAT_TREAT_DURAT.intValue())))
            .andExpect(jsonPath("$.[*].maxDesignTemp").value(hasItem(DEFAULT_MAX_DESIGN_TEMP.intValue())))
            .andExpect(jsonPath("$.[*].heatNumber").value(hasItem(DEFAULT_HEAT_NUMBER)))
            .andExpect(jsonPath("$.[*].coord").value(hasItem(DEFAULT_COORD)))
            .andExpect(jsonPath("$.[*].designCoord").value(hasItem(DEFAULT_DESIGN_COORD.intValue())))
            .andExpect(jsonPath("$.[*].kpStart").value(hasItem(DEFAULT_KP_START.intValue())))
            .andExpect(jsonPath("$.[*].kpEnd").value(hasItem(DEFAULT_KP_END.intValue())))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].idStatus").value(hasItem(DEFAULT_ID_STATUS.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restBuckleArrestorHistMockMvc.perform(get("/api/buckle-arrestor-hists/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultBuckleArrestorHistShouldNotBeFound(String filter) throws Exception {
        restBuckleArrestorHistMockMvc.perform(get("/api/buckle-arrestor-hists?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restBuckleArrestorHistMockMvc.perform(get("/api/buckle-arrestor-hists/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingBuckleArrestorHist() throws Exception {
        // Get the buckleArrestorHist
        restBuckleArrestorHistMockMvc.perform(get("/api/buckle-arrestor-hists/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBuckleArrestorHist() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        int databaseSizeBeforeUpdate = buckleArrestorHistRepository.findAll().size();

        // Update the buckleArrestorHist
        BuckleArrestorHist updatedBuckleArrestorHist = buckleArrestorHistRepository.findById(buckleArrestorHist.getId()).get();
        // Disconnect from session so that the updates on updatedBuckleArrestorHist are not directly saved in db
        em.detach(updatedBuckleArrestorHist);
        updatedBuckleArrestorHist
            .dateFrom(UPDATED_DATE_FROM)
            .dateTo(UPDATED_DATE_TO)
            .idWrk(UPDATED_ID_WRK)
            .serialNum(UPDATED_SERIAL_NUM)
            .diameterOuterSteel(UPDATED_DIAMETER_OUTER_STEEL)
            .internalCoatThickness(UPDATED_INTERNAL_COAT_THICKNESS)
            .externalCoatThickness(UPDATED_EXTERNAL_COAT_THICKNESS)
            .isConcrCoating(UPDATED_IS_CONCR_COATING)
            .concrCoatThickness(UPDATED_CONCR_COAT_THICKNESS)
            .concrCoatDensity(UPDATED_CONCR_COAT_DENSITY)
            .measColWallThickness(UPDATED_MEAS_COL_WALL_THICKNESS)
            .measPipeWallThickness(UPDATED_MEAS_PIPE_WALL_THICKNESS)
            .colLength(UPDATED_COL_LENGTH)
            .pipeLength(UPDATED_PIPE_LENGTH)
            .weight(UPDATED_WEIGHT)
            .smts(UPDATED_SMTS)
            .smys(UPDATED_SMYS)
            .sdbm(UPDATED_SDBM)
            .sdaf(UPDATED_SDAF)
            .sdcs(UPDATED_SDCS)
            .allowTensStrain(UPDATED_ALLOW_TENS_STRAIN)
            .corrosionAllowance(UPDATED_CORROSION_ALLOWANCE)
            .fabricationAllowance(UPDATED_FABRICATION_ALLOWANCE)
            .isBurial(UPDATED_IS_BURIAL)
            .burialDepth(UPDATED_BURIAL_DEPTH)
            .factBurialDepth(UPDATED_FACT_BURIAL_DEPTH)
            .dateManufactured(UPDATED_DATE_MANUFACTURED)
            .millTestPressure(UPDATED_MILL_TEST_PRESSURE)
            .designPressure(UPDATED_DESIGN_PRESSURE)
            .incidentalPressure(UPDATED_INCIDENTAL_PRESSURE)
            .dateInstalled(UPDATED_DATE_INSTALLED)
            .seamOrient(UPDATED_SEAM_ORIENT)
            .seamAngle(UPDATED_SEAM_ANGLE)
            .azimuth(UPDATED_AZIMUTH)
            .seabInstallTemp(UPDATED_SEAB_INSTALL_TEMP)
            .verticalAngle(UPDATED_VERTICAL_ANGLE)
            .heatTreatDurat(UPDATED_HEAT_TREAT_DURAT)
            .maxDesignTemp(UPDATED_MAX_DESIGN_TEMP)
            .heatNumber(UPDATED_HEAT_NUMBER)
            .coord(UPDATED_COORD)
            .designCoord(UPDATED_DESIGN_COORD)
            .kpStart(UPDATED_KP_START)
            .kpEnd(UPDATED_KP_END)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .idStatus(UPDATED_ID_STATUS)
            .description(UPDATED_DESCRIPTION)
            .comment(UPDATED_COMMENT)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        BuckleArrestorHistDTO buckleArrestorHistDTO = buckleArrestorHistMapper.toDto(updatedBuckleArrestorHist);

        restBuckleArrestorHistMockMvc.perform(put("/api/buckle-arrestor-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buckleArrestorHistDTO)))
            .andExpect(status().isOk());

        // Validate the BuckleArrestorHist in the database
        List<BuckleArrestorHist> buckleArrestorHistList = buckleArrestorHistRepository.findAll();
        assertThat(buckleArrestorHistList).hasSize(databaseSizeBeforeUpdate);
        BuckleArrestorHist testBuckleArrestorHist = buckleArrestorHistList.get(buckleArrestorHistList.size() - 1);
        assertThat(testBuckleArrestorHist.getDateFrom()).isEqualTo(UPDATED_DATE_FROM);
        assertThat(testBuckleArrestorHist.getDateTo()).isEqualTo(UPDATED_DATE_TO);
        assertThat(testBuckleArrestorHist.getIdWrk()).isEqualTo(UPDATED_ID_WRK);
        assertThat(testBuckleArrestorHist.getSerialNum()).isEqualTo(UPDATED_SERIAL_NUM);
        assertThat(testBuckleArrestorHist.getDiameterOuterSteel()).isEqualTo(UPDATED_DIAMETER_OUTER_STEEL);
        assertThat(testBuckleArrestorHist.getInternalCoatThickness()).isEqualTo(UPDATED_INTERNAL_COAT_THICKNESS);
        assertThat(testBuckleArrestorHist.getExternalCoatThickness()).isEqualTo(UPDATED_EXTERNAL_COAT_THICKNESS);
        assertThat(testBuckleArrestorHist.getIsConcrCoating()).isEqualTo(UPDATED_IS_CONCR_COATING);
        assertThat(testBuckleArrestorHist.getConcrCoatThickness()).isEqualTo(UPDATED_CONCR_COAT_THICKNESS);
        assertThat(testBuckleArrestorHist.getConcrCoatDensity()).isEqualTo(UPDATED_CONCR_COAT_DENSITY);
        assertThat(testBuckleArrestorHist.getMeasColWallThickness()).isEqualTo(UPDATED_MEAS_COL_WALL_THICKNESS);
        assertThat(testBuckleArrestorHist.getMeasPipeWallThickness()).isEqualTo(UPDATED_MEAS_PIPE_WALL_THICKNESS);
        assertThat(testBuckleArrestorHist.getColLength()).isEqualTo(UPDATED_COL_LENGTH);
        assertThat(testBuckleArrestorHist.getPipeLength()).isEqualTo(UPDATED_PIPE_LENGTH);
        assertThat(testBuckleArrestorHist.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testBuckleArrestorHist.getSmts()).isEqualTo(UPDATED_SMTS);
        assertThat(testBuckleArrestorHist.getSmys()).isEqualTo(UPDATED_SMYS);
        assertThat(testBuckleArrestorHist.getSdbm()).isEqualTo(UPDATED_SDBM);
        assertThat(testBuckleArrestorHist.getSdaf()).isEqualTo(UPDATED_SDAF);
        assertThat(testBuckleArrestorHist.getSdcs()).isEqualTo(UPDATED_SDCS);
        assertThat(testBuckleArrestorHist.getAllowTensStrain()).isEqualTo(UPDATED_ALLOW_TENS_STRAIN);
        assertThat(testBuckleArrestorHist.getCorrosionAllowance()).isEqualTo(UPDATED_CORROSION_ALLOWANCE);
        assertThat(testBuckleArrestorHist.getFabricationAllowance()).isEqualTo(UPDATED_FABRICATION_ALLOWANCE);
        assertThat(testBuckleArrestorHist.getIsBurial()).isEqualTo(UPDATED_IS_BURIAL);
        assertThat(testBuckleArrestorHist.getBurialDepth()).isEqualTo(UPDATED_BURIAL_DEPTH);
        assertThat(testBuckleArrestorHist.getFactBurialDepth()).isEqualTo(UPDATED_FACT_BURIAL_DEPTH);
        assertThat(testBuckleArrestorHist.getDateManufactured()).isEqualTo(UPDATED_DATE_MANUFACTURED);
        assertThat(testBuckleArrestorHist.getMillTestPressure()).isEqualTo(UPDATED_MILL_TEST_PRESSURE);
        assertThat(testBuckleArrestorHist.getDesignPressure()).isEqualTo(UPDATED_DESIGN_PRESSURE);
        assertThat(testBuckleArrestorHist.getIncidentalPressure()).isEqualTo(UPDATED_INCIDENTAL_PRESSURE);
        assertThat(testBuckleArrestorHist.getDateInstalled()).isEqualTo(UPDATED_DATE_INSTALLED);
        assertThat(testBuckleArrestorHist.getSeamOrient()).isEqualTo(UPDATED_SEAM_ORIENT);
        assertThat(testBuckleArrestorHist.getSeamAngle()).isEqualTo(UPDATED_SEAM_ANGLE);
        assertThat(testBuckleArrestorHist.getAzimuth()).isEqualTo(UPDATED_AZIMUTH);
        assertThat(testBuckleArrestorHist.getSeabInstallTemp()).isEqualTo(UPDATED_SEAB_INSTALL_TEMP);
        assertThat(testBuckleArrestorHist.getVerticalAngle()).isEqualTo(UPDATED_VERTICAL_ANGLE);
        assertThat(testBuckleArrestorHist.getHeatTreatDurat()).isEqualTo(UPDATED_HEAT_TREAT_DURAT);
        assertThat(testBuckleArrestorHist.getMaxDesignTemp()).isEqualTo(UPDATED_MAX_DESIGN_TEMP);
        assertThat(testBuckleArrestorHist.getHeatNumber()).isEqualTo(UPDATED_HEAT_NUMBER);
        assertThat(testBuckleArrestorHist.getCoord()).isEqualTo(UPDATED_COORD);
        assertThat(testBuckleArrestorHist.getDesignCoord()).isEqualTo(UPDATED_DESIGN_COORD);
        assertThat(testBuckleArrestorHist.getKpStart()).isEqualTo(UPDATED_KP_START);
        assertThat(testBuckleArrestorHist.getKpEnd()).isEqualTo(UPDATED_KP_END);
        assertThat(testBuckleArrestorHist.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testBuckleArrestorHist.getIdStatus()).isEqualTo(UPDATED_ID_STATUS);
        assertThat(testBuckleArrestorHist.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testBuckleArrestorHist.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testBuckleArrestorHist.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testBuckleArrestorHist.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testBuckleArrestorHist.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testBuckleArrestorHist.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingBuckleArrestorHist() throws Exception {
        int databaseSizeBeforeUpdate = buckleArrestorHistRepository.findAll().size();

        // Create the BuckleArrestorHist
        BuckleArrestorHistDTO buckleArrestorHistDTO = buckleArrestorHistMapper.toDto(buckleArrestorHist);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBuckleArrestorHistMockMvc.perform(put("/api/buckle-arrestor-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buckleArrestorHistDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BuckleArrestorHist in the database
        List<BuckleArrestorHist> buckleArrestorHistList = buckleArrestorHistRepository.findAll();
        assertThat(buckleArrestorHistList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBuckleArrestorHist() throws Exception {
        // Initialize the database
        buckleArrestorHistRepository.saveAndFlush(buckleArrestorHist);

        int databaseSizeBeforeDelete = buckleArrestorHistRepository.findAll().size();

        // Delete the buckleArrestorHist
        restBuckleArrestorHistMockMvc.perform(delete("/api/buckle-arrestor-hists/{id}", buckleArrestorHist.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<BuckleArrestorHist> buckleArrestorHistList = buckleArrestorHistRepository.findAll();
        assertThat(buckleArrestorHistList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BuckleArrestorHist.class);
        BuckleArrestorHist buckleArrestorHist1 = new BuckleArrestorHist();
        buckleArrestorHist1.setId(1L);
        BuckleArrestorHist buckleArrestorHist2 = new BuckleArrestorHist();
        buckleArrestorHist2.setId(buckleArrestorHist1.getId());
        assertThat(buckleArrestorHist1).isEqualTo(buckleArrestorHist2);
        buckleArrestorHist2.setId(2L);
        assertThat(buckleArrestorHist1).isNotEqualTo(buckleArrestorHist2);
        buckleArrestorHist1.setId(null);
        assertThat(buckleArrestorHist1).isNotEqualTo(buckleArrestorHist2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BuckleArrestorHistDTO.class);
        BuckleArrestorHistDTO buckleArrestorHistDTO1 = new BuckleArrestorHistDTO();
        buckleArrestorHistDTO1.setId(1L);
        BuckleArrestorHistDTO buckleArrestorHistDTO2 = new BuckleArrestorHistDTO();
        assertThat(buckleArrestorHistDTO1).isNotEqualTo(buckleArrestorHistDTO2);
        buckleArrestorHistDTO2.setId(buckleArrestorHistDTO1.getId());
        assertThat(buckleArrestorHistDTO1).isEqualTo(buckleArrestorHistDTO2);
        buckleArrestorHistDTO2.setId(2L);
        assertThat(buckleArrestorHistDTO1).isNotEqualTo(buckleArrestorHistDTO2);
        buckleArrestorHistDTO1.setId(null);
        assertThat(buckleArrestorHistDTO1).isNotEqualTo(buckleArrestorHistDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(buckleArrestorHistMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(buckleArrestorHistMapper.fromId(null)).isNull();
    }
}
