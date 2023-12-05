import { z } from "zod"

const ChapterSchema = z.object({
    name: z.string().min(1).max(30),
    number: z.number().positive(),
})

export type Chapter = z.infer<typeof ChapterSchema>