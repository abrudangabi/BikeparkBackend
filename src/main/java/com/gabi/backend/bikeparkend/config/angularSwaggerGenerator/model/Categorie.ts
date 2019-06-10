/**
 * Api Documentation
 * Api Documentation
 *
 * OpenAPI spec version: 1.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

import * as models from './models';

export interface Categorie {
    dificultate?: Categorie.DificultateEnum;

    id?: number;

    lungime?: number;

    tipDisciplina?: Categorie.TipDisciplinaEnum;

    varsta?: Categorie.VarstaEnum;

}
export namespace Categorie {
    export enum DificultateEnum {
        Greu = <any> 'greu',
        Mediu = <any> 'mediu',
        Usor = <any> 'usor'
    }
    export enum TipDisciplinaEnum {
        XC = <any> 'XC',
        ENDURO = <any> 'ENDURO',
        DH = <any> 'DH',
        ROAD = <any> 'ROAD',
        SLOPE = <any> 'SLOPE'
    }
    export enum VarstaEnum {
        COPIL = <any> 'COPIL',
        TANAR = <any> 'TANAR',
        ADULT = <any> 'ADULT',
        BATRAN = <any> 'BATRAN'
    }
}
