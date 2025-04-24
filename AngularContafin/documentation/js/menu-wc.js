'use strict';

customElements.define('compodoc-menu', class extends HTMLElement {
    constructor() {
        super();
        this.isNormalMode = this.getAttribute('mode') === 'normal';
    }

    connectedCallback() {
        this.render(this.isNormalMode);
    }

    render(isNormalMode) {
        let tp = lithtml.html(`
        <nav>
            <ul class="list">
                <li class="title">
                    <a href="index.html" data-type="index-link">angular-contafin documentation</a>
                </li>

                <li class="divider"></li>
                ${ isNormalMode ? `<div id="book-search-input" role="search"><input type="text" placeholder="Type to search"></div>` : '' }
                <li class="chapter">
                    <a data-type="chapter-link" href="index.html"><span class="icon ion-ios-home"></span>Getting started</a>
                    <ul class="links">
                        <li class="link">
                            <a href="overview.html" data-type="chapter-link">
                                <span class="icon ion-ios-keypad"></span>Overview
                            </a>
                        </li>
                        <li class="link">
                            <a href="index.html" data-type="chapter-link">
                                <span class="icon ion-ios-paper"></span>README
                            </a>
                        </li>
                                <li class="link">
                                    <a href="dependencies.html" data-type="chapter-link">
                                        <span class="icon ion-ios-list"></span>Dependencies
                                    </a>
                                </li>
                                <li class="link">
                                    <a href="properties.html" data-type="chapter-link">
                                        <span class="icon ion-ios-apps"></span>Properties
                                    </a>
                                </li>
                    </ul>
                </li>
                    <li class="chapter modules">
                        <a data-type="chapter-link" href="modules.html">
                            <div class="menu-toggler linked" data-bs-toggle="collapse" ${ isNormalMode ?
                                'data-bs-target="#modules-links"' : 'data-bs-target="#xs-modules-links"' }>
                                <span class="icon ion-ios-archive"></span>
                                <span class="link-name">Modules</span>
                                <span class="icon ion-ios-arrow-down"></span>
                            </div>
                        </a>
                        <ul class="links collapse " ${ isNormalMode ? 'id="modules-links"' : 'id="xs-modules-links"' }>
                            <li class="link">
                                <a href="modules/AppModule.html" data-type="entity-link" >AppModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ?
                                            'data-bs-target="#components-links-module-AppModule-a163596b8cd1cf7ec9aadf4de2d5210311713edcb97250d74e893a73174f2d4eddf0067ab88154f7c2a89f0c61997632f9d71e627d0cb5f168cb25b17eff42f0"' : 'data-bs-target="#xs-components-links-module-AppModule-a163596b8cd1cf7ec9aadf4de2d5210311713edcb97250d74e893a73174f2d4eddf0067ab88154f7c2a89f0c61997632f9d71e627d0cb5f168cb25b17eff42f0"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-AppModule-a163596b8cd1cf7ec9aadf4de2d5210311713edcb97250d74e893a73174f2d4eddf0067ab88154f7c2a89f0c61997632f9d71e627d0cb5f168cb25b17eff42f0"' :
                                            'id="xs-components-links-module-AppModule-a163596b8cd1cf7ec9aadf4de2d5210311713edcb97250d74e893a73174f2d4eddf0067ab88154f7c2a89f0c61997632f9d71e627d0cb5f168cb25b17eff42f0"' }>
                                            <li class="link">
                                                <a href="components/AdminComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >AdminComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/AdminUserDataComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >AdminUserDataComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/AppComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >AppComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/BodyCompleteLessonComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >BodyCompleteLessonComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/BodyContinueLessonComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >BodyContinueLessonComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/BodyErrorComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >BodyErrorComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/BodyExerciseComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >BodyExerciseComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/BodyHomeComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >BodyHomeComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/BodyIndexComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >BodyIndexComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/BodyLessonComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >BodyLessonComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/CompleteLessonComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >CompleteLessonComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/ContinueLessonComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >ContinueLessonComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/ErrorComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >ErrorComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/Exercise1Component.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >Exercise1Component</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/Exercise2Component.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >Exercise2Component</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/Exercise5Component.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >Exercise5Component</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/Exercise7Component.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >Exercise7Component</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/ExerciseComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >ExerciseComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/HeaderExerciseComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >HeaderExerciseComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/HeaderNavbarComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >HeaderNavbarComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/HomeComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >HomeComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/IndexComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >IndexComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/LessonComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >LessonComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/LoginComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >LoginComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/NavBarComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >NavBarComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/SignUpComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >SignUpComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/UnitCreationComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >UnitCreationComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/UnitListComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >UnitListComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/UserConfigurationComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >UserConfigurationComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/UserGoalComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >UserGoalComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/UserProfileComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >UserProfileComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                                <li class="chapter inner">
                                    <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ?
                                        'data-bs-target="#injectables-links-module-AppModule-a163596b8cd1cf7ec9aadf4de2d5210311713edcb97250d74e893a73174f2d4eddf0067ab88154f7c2a89f0c61997632f9d71e627d0cb5f168cb25b17eff42f0"' : 'data-bs-target="#xs-injectables-links-module-AppModule-a163596b8cd1cf7ec9aadf4de2d5210311713edcb97250d74e893a73174f2d4eddf0067ab88154f7c2a89f0c61997632f9d71e627d0cb5f168cb25b17eff42f0"' }>
                                        <span class="icon ion-md-arrow-round-down"></span>
                                        <span>Injectables</span>
                                        <span class="icon ion-ios-arrow-down"></span>
                                    </div>
                                    <ul class="links collapse" ${ isNormalMode ? 'id="injectables-links-module-AppModule-a163596b8cd1cf7ec9aadf4de2d5210311713edcb97250d74e893a73174f2d4eddf0067ab88154f7c2a89f0c61997632f9d71e627d0cb5f168cb25b17eff42f0"' :
                                        'id="xs-injectables-links-module-AppModule-a163596b8cd1cf7ec9aadf4de2d5210311713edcb97250d74e893a73174f2d4eddf0067ab88154f7c2a89f0c61997632f9d71e627d0cb5f168cb25b17eff42f0"' }>
                                        <li class="link">
                                            <a href="injectables/AdminService.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >AdminService</a>
                                        </li>
                                        <li class="link">
                                            <a href="injectables/ErrorService.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >ErrorService</a>
                                        </li>
                                        <li class="link">
                                            <a href="injectables/ExerciseService.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >ExerciseService</a>
                                        </li>
                                        <li class="link">
                                            <a href="injectables/LessonsService.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >LessonsService</a>
                                        </li>
                                        <li class="link">
                                            <a href="injectables/LoginService.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >LoginService</a>
                                        </li>
                                        <li class="link">
                                            <a href="injectables/SignUpService.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >SignUpService</a>
                                        </li>
                                        <li class="link">
                                            <a href="injectables/UnitsService.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >UnitsService</a>
                                        </li>
                                        <li class="link">
                                            <a href="injectables/UserService.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >UserService</a>
                                        </li>
                                    </ul>
                                </li>
                            </li>
                </ul>
                </li>
                        <li class="chapter">
                            <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ? 'data-bs-target="#injectables-links"' :
                                'data-bs-target="#xs-injectables-links"' }>
                                <span class="icon ion-md-arrow-round-down"></span>
                                <span>Injectables</span>
                                <span class="icon ion-ios-arrow-down"></span>
                            </div>
                            <ul class="links collapse " ${ isNormalMode ? 'id="injectables-links"' : 'id="xs-injectables-links"' }>
                                <li class="link">
                                    <a href="injectables/AdminService.html" data-type="entity-link" >AdminService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/ErrorService.html" data-type="entity-link" >ErrorService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/ExerciseService.html" data-type="entity-link" >ExerciseService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/LessonsService.html" data-type="entity-link" >LessonsService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/LoginService.html" data-type="entity-link" >LoginService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/SignUpService.html" data-type="entity-link" >SignUpService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/TokenStorageService.html" data-type="entity-link" >TokenStorageService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/UnitsService.html" data-type="entity-link" >UnitsService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/UserService.html" data-type="entity-link" >UserService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/UtilsService.html" data-type="entity-link" >UtilsService</a>
                                </li>
                            </ul>
                        </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ? 'data-bs-target="#interceptors-links"' :
                            'data-bs-target="#xs-interceptors-links"' }>
                            <span class="icon ion-ios-swap"></span>
                            <span>Interceptors</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? 'id="interceptors-links"' : 'id="xs-interceptors-links"' }>
                            <li class="link">
                                <a href="interceptors/AuthInterceptor.html" data-type="entity-link" >AuthInterceptor</a>
                            </li>
                        </ul>
                    </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ? 'data-bs-target="#guards-links"' :
                            'data-bs-target="#xs-guards-links"' }>
                            <span class="icon ion-ios-lock"></span>
                            <span>Guards</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? 'id="guards-links"' : 'id="xs-guards-links"' }>
                            <li class="link">
                                <a href="guards/CanActivateAdmin.html" data-type="entity-link" >CanActivateAdmin</a>
                            </li>
                            <li class="link">
                                <a href="guards/CanActivateUser.html" data-type="entity-link" >CanActivateUser</a>
                            </li>
                        </ul>
                    </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ? 'data-bs-target="#interfaces-links"' :
                            'data-bs-target="#xs-interfaces-links"' }>
                            <span class="icon ion-md-information-circle-outline"></span>
                            <span>Interfaces</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? ' id="interfaces-links"' : 'id="xs-interfaces-links"' }>
                            <li class="link">
                                <a href="interfaces/Answer.html" data-type="entity-link" >Answer</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/CompletedLesson.html" data-type="entity-link" >CompletedLesson</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/Exercise.html" data-type="entity-link" >Exercise</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/Lesson.html" data-type="entity-link" >Lesson</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/Unit.html" data-type="entity-link" >Unit</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/User.html" data-type="entity-link" >User</a>
                            </li>
                        </ul>
                    </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ? 'data-bs-target="#miscellaneous-links"'
                            : 'data-bs-target="#xs-miscellaneous-links"' }>
                            <span class="icon ion-ios-cube"></span>
                            <span>Miscellaneous</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? 'id="miscellaneous-links"' : 'id="xs-miscellaneous-links"' }>
                            <li class="link">
                                <a href="miscellaneous/variables.html" data-type="entity-link">Variables</a>
                            </li>
                        </ul>
                    </li>
                    <li class="chapter">
                        <a data-type="chapter-link" href="coverage.html"><span class="icon ion-ios-stats"></span>Documentation coverage</a>
                    </li>
                    <li class="divider"></li>
                    <li class="copyright">
                        Documentation generated using <a href="https://compodoc.app/" target="_blank" rel="noopener noreferrer">
                            <img data-src="images/compodoc-vectorise.png" class="img-responsive" data-type="compodoc-logo">
                        </a>
                    </li>
            </ul>
        </nav>
        `);
        this.innerHTML = tp.strings;
    }
});