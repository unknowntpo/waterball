interface Student {
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

type MissionState = "ON_GOING" | "COMPLETED"

interface MissionCarryOn {
    state: MissionState
}

const carryOn(student: Student, mission: Mission): MissionCarryOn => ({
    state: "ON_GOING",
})

const completeMission(missionCarryOn: MissionCarryOn) => missionCarryOn {
    // TODO: calculateAward

}
