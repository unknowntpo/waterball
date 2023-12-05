import { z } from "zod"

const TourGroupSchema = z.object({
    number: z.number().positive(),
})

export type TourGroup = z.infer<typeof TourGroupSchema>