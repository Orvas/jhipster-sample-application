export interface IListObjectStatus {
  id?: number;
  code?: string;
  name?: string;
  fullName?: string;
  isCurrentFlag?: boolean;
}

export const defaultValue: Readonly<IListObjectStatus> = {
  isCurrentFlag: false
};
