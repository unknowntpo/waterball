import { z } from "zod"

const StudentSchema = z.object({
    account: z.string().min(1),
    password: z.string().min(1),
    level: z.number().default(1),
    exp: z.number().default(0),
})

type Student = z.infer<typeof StudentSchema>

const gainExp = (student: Student, exp: number): Student => ({
    ...student,
    exp: student.exp += exp,
})

const levelUp = (student: Student): Student => ({
    ...student,
    level: student.level++,
})