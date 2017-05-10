import { MhsClientPage } from './app.po';

describe('mhs-client App', () => {
  let page: MhsClientPage;

  beforeEach(() => {
    page = new MhsClientPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
