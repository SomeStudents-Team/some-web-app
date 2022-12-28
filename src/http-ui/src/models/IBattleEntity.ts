import {IBattleClientEntity} from "./IBattleClientEntity";
import {IBattleMemberEntity} from "./IBattleMemberEntity";
import {BattleEntityState} from "./BattleEntityState";

export interface IBattleEntity {

    id: bigint,

    title: string,

    description: string,

    date_start: string,

    state: BattleEntityState,

    owner: IBattleClientEntity,

    leader: IBattleMemberEntity | null,

    members: IBattleMemberEntity[]
}