export const makeClassName = (
  setting: string, // a
  _className?: string, // b
  numberOfLines?: number, // 1
  // a b line-clamp-1
) =>
  [
    setting,
    numberOfLines ? `line-clamp-${numberOfLines}` : "",
    _className,
  ].join(" ");
