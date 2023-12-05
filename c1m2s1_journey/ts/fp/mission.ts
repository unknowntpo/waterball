enum MissionState {
    OnGoing,
    Completed,
}

type Mission = {
    name: string,
    number: number
    scenes: Array<Scene>
}

const calculateMissionExpAward = (target: Mission): number => {
    return target.scenes.map((scene: Scene): number => calculateSceneExpAward(scene)).reduce((acc: number, award: number) => acc + award), 0)
}

const completeMissionCarryOn = (missionCarryOn: MissionCarryOn): MissionCarryOn => ({
    ...missionCarryOn,
    state: MissionState.Completed
})

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