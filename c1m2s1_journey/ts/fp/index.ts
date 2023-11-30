import { on } from "events"
import { execPath } from "process"
import { Context } from "vm"

type Student = {
    account: string,
    password: string,
    level: number,
    exp: number
}

const gainExp(student: Student, exp: number): Student => ({
    ...student,
    exp: student.exp += exp,
})

const levelUp(student: Student): Student => ({
    ...student,
    level: student.level++,
})

enum MissionState {
    OnGoing,
    Completed,
}

type Mission = {
    name: string,
    number: number
    scenes: Array<Scene>
}

type Chapter = {
    name: string,
    number: number
}

type Scene = {
    name: string,
    number: number,
    expAward: number
    typ: SceneType,
}

enum SceneType {
    Video,
    Content
}

type VideoScene = Scene & { typ: SceneType.Video }
type ContentScene = Scene & { typ: SceneType.Content }

type Challenge = {
    name: string,
    number: number
}

// type MissionState = "ON_GOING" | "COMPLETED"

type MissionCarryOn = {
    state: MissionState
    mission: Mission
}

const carryOn = (student: Student, mission: Mission): MissionCarryOn => ({
    mission: mission,
    state: MissionState.OnGoing,
})

type ExpAwardable = Mission | Scene

type expCalculateFn = (target: ExpAwardable) => number;

const calculateExpAward = (target: ExpAwardable, fn: expCalculateFn): number => {
    return fn(target)
}

const calculateVideoSceneExpAward = (target: VideoScene): number => Math.floor(target.expAward * 1.5)
const calculateContentSceneExpAward = (target: ContentScene): number => Math.floor(target.expAward * 1.1)

const calculateSceneExpAward = (target: Scene): number => {
    switch (target.typ) {
        case (SceneType.Video):
            return calculateVideoSceneExpAward(target as VideoScene)
        case (SceneType.Content):
            return calculateContentSceneExpAward(target as ContentScene)
    }
}

const calculateMissionExpAward = (target: Mission): number => {
    return target.scenes.map((scene: Scene): number => calculateSceneExpAward(scene)).reduce((acc: number, award: number) => acc + award), 0)
}

const completeMissionCarryOn = (missionCarryOn: MissionCarryOn): MissionCarryOn => ({
    ...missionCarryOn,
    state: MissionState.Completed
})