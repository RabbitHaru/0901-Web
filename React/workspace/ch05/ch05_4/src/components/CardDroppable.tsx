import { Droppable } from "@hello-pangea/dnd"
import type { FC, PropsWithChildren } from "react"

export type CardDropableProps = {
    droppableId: string
}
export const CardDroppable: FC<PropsWithChildren<CardDropableProps>> = ({
    droppableId,
    children
}) => {
    return (
        <Droppable droppableId={droppableId}>
            {provided => (
                <div
                    {...provided.droppableProps}
                    ref={provided.innerRef}
                    className="flex flex-col p-2">
                    {children}
                    {provided.placeholder}
                    </div>
            )}
        </Droppable>
    )
}