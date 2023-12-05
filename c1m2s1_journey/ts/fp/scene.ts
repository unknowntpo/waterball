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