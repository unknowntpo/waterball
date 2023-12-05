import { z } from "zod"
import { StudentSchema, Student } from "./student"
import { AsyncHook } from "async_hooks"
import { TourGroup } from "./tourGroup"
import { Chapter } from "./Chapter"
import { describe } from "node:test"


// const AdventurerSchema = z.object({
//     number: z.number().positive()
// })

// type Adventurer = z.infer<typeof AdventurerSchema>

// const AdventurersSchema = z.object({
//     adventurers: z.array(AdventurerSchema),
// })

// type Adventurers = z.infer<typeof AdventurerSchema>

// const TourGroupsSchema = z.object({
//     tourGroups: z.array(TourGroupSchema),
// })

// type TourGroups = z.infer<typeof TourGroupsSchema>

const baseJourneySchema = z.object({
    name: z.string().min(1).max(30),
    description: z.string().min(0).max(300),
    price: z.number().min(1),
})

type baseJourney = z.infer<typeof baseJourneySchema>

type Journey = baseJourney & {
    adventurers: Array<Adventurer>,
    tourGroups: Array<TourGroup>,
    chapters: Array<Chapter>,
}

const createJourney = (
    name: string,
    description: string,
    price: number,
    adventurers: Array<Adventurer>,
    tourGroups: Array<TourGroup>,
    chapters: Array<Chapter>
): Journey => ({
    name: name,
    description: description,
    price: price,
    adventurers: adventurers,
    tourGroups: tourGroups,
    chapters: chapters,
})



const joinStudentToJourney = (journey: Journey, student: Student): Adventurer => {
    // int number = adventurers.size() + 1;

    // // 建立與冒險者的雙向關聯
    // Adventurer adventurer = new Adventurer(number, student, this);
    // adventurers.add(adventurer);
    // student.getAdventurers().add(adventurer);

    // // 開始第一項任務
    // Mission firstMission = getFirstMission();
    // adventurer.carryOn(firstMission);

    // // 匹配旅團
    // TourGroup tourGroup = matchTourGroup(adventurer);
    // tourGroup.add(adventurer);
    // System.out.printf("【旅程】冒險者 %s 加入旅程 %s --> 匹配至旅團 %d。\n",
    //         student.getAccount(), getName(), tourGroup.getNumber());
    // return adventurer;
    let num = journey.adventurers.length + 1
    let adventurer = createAdventurer(num, student, journey)
    let firstMission = getFirstMissionFromJourney(journey)
    return {
        number: 1
    }
}

const createAdventurer = (number: number, student: Student, journey: Journey): Adventurer => ({
    number,
    student,
    journey,
})
const baseAdventurerSchema = z.object({
    number: z.number().positive(),
})

type Adventurer = z.infer<typeof baseAdventurerSchema> & {
    student: Student,
    journey: Journey,
}

function getFirstMissionFromJourney(journey: Journey) {
    return journey.chapters[0]
}